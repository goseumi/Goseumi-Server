package project.goseumi.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.request.LoginRequest;
import project.goseumi.controller.dto.request.SchoolAuthAccessRequest;
import project.goseumi.controller.dto.request.SchoolAuthRequest;
import project.goseumi.controller.dto.request.SignUpRequest;
import project.goseumi.controller.dto.response.LoginResponse;
import project.goseumi.domain.Member;
import project.goseumi.domain.School;
import project.goseumi.domain.SchoolAuth;
import project.goseumi.exception.BusinessException;
import project.goseumi.exception.error.MemberError;
import project.goseumi.exception.error.SchoolAuthError;
import project.goseumi.exception.error.SchoolError;
import project.goseumi.repository.MemberRepository;
import project.goseumi.repository.SchoolAuthRepository;
import project.goseumi.repository.SchoolRepository;
import project.goseumi.security.jwt.JwtUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final SchoolAuthRepository schoolAuthRepository;
    private final SchoolRepository schoolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public Long createNewAccount(final SignUpRequest signUpRequest) {
        chkEmailDuplicate(signUpRequest.getEmail());
        chkNicknameDuplicate(signUpRequest.getNickname());
        String encodedPassword =
                chkPasswordAndEncode(signUpRequest.getPassword(), signUpRequest.getPasswordCheck());

        Member signUpMember = Member.signUp(signUpRequest, encodedPassword);
        Member savedMember = memberRepository.save(signUpMember);

        return savedMember.getId();
    }

    /**
     * 이메일 중복확인
     */
    private void chkEmailDuplicate(final String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent()) {
            throw new BusinessException(MemberError.EMAIL_DUPLICATE);
        }
    }

    /**
     * 닉네임 중복확인
     */
    private void chkNicknameDuplicate(final String nickname) {
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);
        if (optionalMember.isPresent()) {
            throw new BusinessException(MemberError.NICKNAME_DUPLICATE);
        }
    }

    /**
     * 패스워드 중복체크 및 암호화
     */
    private String chkPasswordAndEncode(final String password, final String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new BusinessException(MemberError.PASSWORD_MISMATCH);
        }
        return passwordEncoder.encode(password);
    }

    public LoginResponse loginWithEmailAndPassword(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(MemberError.USER_LOGIN_FAIL));
        validatePassword(password, member.getPassword());
        return null;

    }

    private void validatePassword(final String inputPassword, final String expectedPassword) {
        if (!passwordEncoder.matches(inputPassword, expectedPassword)) {
            throw new BusinessException(MemberError.USER_LOGIN_FAIL);
        }
    }

    /**
     * 학교 인증 생성 로직
     */
    @Transactional
    public Long newSchoolAuth(HttpServletRequest request, SchoolAuthRequest schoolAuthRequest) {
        String userEmail = getUserEmailFromToken(request);

        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(MemberError.USER_LOGIN_FAIL));

        School school = schoolRepository.findById(schoolAuthRequest.getSchoolId())
                .orElseThrow(() -> new BusinessException(SchoolError.SCHOOL_FIND_BY_ID_FAIL));

        SchoolAuth schoolAuth = SchoolAuth.of(member, school, schoolAuthRequest.getImgUrl());

        schoolAuthRepository.save(schoolAuth);

        return schoolAuth.getId();
    }

    /**
     * 토큰에서 username(email) 뽑아오기
     */
    private String getUserEmailFromToken(HttpServletRequest request) {
        String pureToken = getPureToken(request);

        return jwtUtil.getUsername(pureToken);
    }

    /**
     * Authorization 헤더에서 Bearer 제거하고 순수 토큰 반환
     */
    private String getPureToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new BusinessException(MemberError.NOT_EXIST_TOKEN);
        }

        return authorization.split(" ")[1];
    }

    /**
     * 학교 인증 승인
     */
    @Transactional
    public Long accessSchoolAuth(SchoolAuthAccessRequest schoolAuthAccessRequest) {

        SchoolAuth schoolAuth = schoolAuthRepository.findById(schoolAuthAccessRequest.getId())
                .orElseThrow(() -> new BusinessException(SchoolAuthError.SCHOOL_AUTH_FIND_BY_ID_FAIL));

        schoolAuth.authAccess();
        Member member = schoolAuth.getMember();

        member.updateSchoolInfo(schoolAuth.getSchool());

        return schoolAuth.getId();
    }
}
