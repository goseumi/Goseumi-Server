package project.goseumi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.request.SignUpRequest;
import project.goseumi.domain.Member;
import project.goseumi.exception.BusinessException;
import project.goseumi.exception.error.MemberError;
import project.goseumi.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
}
