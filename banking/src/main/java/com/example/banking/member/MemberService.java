package com.example.banking.member;

import com.example.banking.DataNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Member> getList(){
        return this.memberRepository.findAll();
    }

    public Member getMember(Long id) {
        Optional<Member> member = this.memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public void create(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setCreateDate(LocalDateTime.now());
        System.out.println(" " + member.toString());
        this.memberRepository.save(member);
    }
}
