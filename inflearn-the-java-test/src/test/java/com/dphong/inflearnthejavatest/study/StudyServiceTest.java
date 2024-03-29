package com.dphong.inflearnthejavatest.study;

import com.dphong.inflearnthejavatest.domain.Member;
import com.dphong.inflearnthejavatest.domain.Study;
import com.dphong.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Test
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("dphong@email.com");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

//        Study study = new Study(1, "dphong");
//        studyService.createNewStudy(1L, study);

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("dphong@email.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(2L));

        assertNotNull(studyService);

//        verify(memberService, times(3)).notify(study);
//        verify(memberService, never()).validate(any());

//        InOrder inOrder = inOrder(memberService);
//        inOrder.verify(memberService).notify(study);
//        verifyNoInteractions(memberService);
    }

    @Test
    void stubbing_test(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        Study study = new Study(10, "테스트");
        Member member = new Member();
        member.setId(1L);
        member.setEmail("test@mail");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

//        given(memberService.findById(1L)).willReturn(Optional.of(member));

        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createNewStudy(1L, study);

        assertNotNull(study.getName());
//        assertEquals(member.getId(), study.get());

        then(memberService).should(times(1)).notify(study);
    }
}