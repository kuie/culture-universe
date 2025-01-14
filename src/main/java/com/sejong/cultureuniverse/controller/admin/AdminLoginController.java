package com.sejong.cultureuniverse.controller.admin;

import com.sejong.cultureuniverse.dto.admin.AdminDTO;
import com.sejong.cultureuniverse.service.admin.AdminService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminLoginController {
    
    private final AdminService loginService;
    
    
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("adminLoginDTO") AdminDTO adminDTO) {
        return "admin/login/loginForm";
    }
    
//    @PostMapping("/login")
//    public String login(@Validated @ModelAttribute AdminDTO adminDTO,
//        BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL,
//        HttpServletRequest request) {
//        if (bindingResult.hasErrors()) {
//            return "admin/login/loginForm";
//        }
//
//        Admin loginAdmin = loginService.login(adminDTO.getAdminId(),
//            adminDTO.getAdminPw());
//
//        if (loginAdmin == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "admin/login/loginForm";
//        }
//        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
//        HttpSession session = request.getSession();
//        //세션에 로그인 회원 정보 보관
//        session.setAttribute(SessionConst.LOGIN_ADMIN, loginAdmin);
//
//        log.info(redirectURL);
//
//        return "redirect:" + redirectURL;
//
//    }

//    @RequestMapping("/logout")
//    public String logoutV3(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/admin/loginForm";
    }
    
    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        model.addAttribute("username", username);
        model.addAttribute("exception",exception);
        
        return "/admin/denied";
        
    }
}
