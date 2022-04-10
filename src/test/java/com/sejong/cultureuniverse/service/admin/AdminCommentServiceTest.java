package com.sejong.cultureuniverse.service.admin;

import com.sejong.cultureuniverse.dto.AdminCommentDTO;
import com.sejong.cultureuniverse.entity.admin.AdminComment;
import com.sejong.cultureuniverse.service.AdminCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AdminCommentServiceTest {

    @Autowired
    AdminCommentService adminCommentService;

    @Test
    public void getListComment() {
        Long questionIdx = 18L;
        List<AdminComment> adminCommentDTOList = adminCommentService.getList(questionIdx);
        for (AdminComment adminComment : adminCommentDTOList) {
            System.out.println("adminComment = " + adminComment.toString());
        }

    }
}
