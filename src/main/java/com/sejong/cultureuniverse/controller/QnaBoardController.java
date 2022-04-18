package com.sejong.cultureuniverse.controller;


import com.sejong.cultureuniverse.SessionConst;
import com.sejong.cultureuniverse.dto.AdminCommentDTO;
import com.sejong.cultureuniverse.dto.AdminCommentListDTO;
import com.sejong.cultureuniverse.dto.QnaBoardDTO;
import com.sejong.cultureuniverse.dto.paging.PageRequestDTO;
import com.sejong.cultureuniverse.entity.admin.Admin;
import com.sejong.cultureuniverse.service.AdminCommentService;
import com.sejong.cultureuniverse.service.QnaBoardService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class QnaBoardController {
    private final QnaBoardService qnaBoardService;
    private final AdminCommentService adminCommentService;


    @GetMapping("/qnalist")
    public void qnaList(PageRequestDTO pageRequestDTO, Model model) {
        log.info("qnalist" + pageRequestDTO);
        model.addAttribute("result", qnaBoardService.getList(pageRequestDTO));
    }

    @GetMapping("/qnaread")
    public void read(long questionIdx, Model model,HttpServletRequest request) {
        QnaBoardDTO qnaBoardDTO = qnaBoardService.get(questionIdx);
        List<AdminCommentListDTO> list = adminCommentService.getList(questionIdx);
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(SessionConst.LOGIN_ADMIN);
    
        model.addAttribute("admin", admin);
        model.addAttribute("qnadto", qnaBoardDTO);
        model.addAttribute("commentlist", list);
    }

    //    댓글 controller
    @PostMapping("/qnaregister/{questionIdx}")
    public String commentRegister(@PathVariable("questionIdx") Long questionIdx,
                                  AdminCommentDTO adminCommentDTO,PageRequestDTO pageRequestDTO,
                                  HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) {
        adminCommentService.register(adminCommentDTO,request);
        redirectAttributes.addAttribute("questionIdx", questionIdx);
        return "redirect:/admin/qnaread";
    }

    @PostMapping("/commentdelete/{commentIdx}")
    public String delete(@PathVariable("commentIdx") long commentIdx, RedirectAttributes redirectAttributes, long questionIdx) {
        adminCommentService.delete(commentIdx);
        redirectAttributes.addAttribute("questionIdx",questionIdx);
        return "redirect:/admin/qnaread";
    }
}


