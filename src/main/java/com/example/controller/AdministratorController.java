package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者用コントローラー
 * ログインや登録をするメソッドを格納
 * @author 川内野孝樹
 */
@Controller
@RequestMapping("")
public class AdministratorController {

    /**
     * 
     * sessionスコープ作成
     */
    @Autowired
    private HttpSession session;

    /**
     * 
     * 管理者用のサービスクラスのメソッドが使えるようにする
     */
    @Autowired
    private AdministratorService administratorService;

    /**
     * 
     * 従業員登録する際のリクエストパラメータを格納 
     * @return 管理者登録画面へ遷移
     * @param InsertAdministratorForm フォームオブジェクトをリクエストスコープに⾃動的に格納
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 
     * 管理者情報を登録し、ログイン画面へリダイレクト
     * @return ログイン画面へリダイレクト 
     * @param InsertAdministratorForm フォームオブジェクトをリクエストスコープに⾃動的に格納
     */
    @PostMapping("/insert")
    public String insert(@Validated InsertAdministratorForm form, BindingResult result) {
        if (result.hasErrors()) {
            return toInsert(form);
        }
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }
    
    /**
     * 
     * insertメソッドのリダイレクトを受ける
     * @return ログイン画面へ遷移
     * @param LoginForm フォームオブジェクトをリクエストスコープに⾃動的に格納
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * 
     * ログイン画面でパスワードとメールアドレスを入力し
     * @return 不正ならエラー文とともにログイン画面に戻る
     * @return 正当なら従業員画面へ遷移
     * @param LoginForm フォームオブジェクトをリクエストスコープに⾃動的に格納
     * @param Model リクエストスコープに格納できるようにする
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        //空欄のときのエラー処理
        if (form.getMailAddress().isEmpty() || form.getPassword().isEmpty()) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        //登録されていないときのエラー処理
        }
        if (administrator == null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        } else {
            session.setAttribute("administratorName", administrator.getName());
            return "redirect:/employee/showList";
        }
    }

    /**
     * 
     * セッションスコープの中身を消し、ログアウト処理
     * @return /へリダイレクト
     * @param LoginForm フォームオブジェクトをリクエストスコープに⾃動的に格納
     */
    @GetMapping("/logout")
    public String logout(LoginForm form) {
        session.invalidate();
        return "administrator/login";
    }
}
