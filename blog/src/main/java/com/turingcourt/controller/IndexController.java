package com.turingcourt.controller;

import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultCode;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.entity.User;
import com.turingcourt.service.UserLoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * 通用操作
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:52
 */
@Validated
@RestController
@ApiOperation("通用操作")
public class IndexController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public JsonResult register(@Validated @RequestBody User user) {
        try {
            Boolean registered = userLoginService.register(user);
            if (registered) {
                return ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail(ResultCode.COMMON_FAIL, "注册失败");
    }

    /**
     * 帐号校验
     *
     * @param username 用户账号
     * @return 是否已经注册
     */
    @GetMapping("/checkAccount")
    @ApiOperation("账号校验")
    public JsonResult checkAccount(@NotBlank(message = "用户名不应为空") String username) {
        Boolean isExist = userLoginService.checkAccount(username);
        if (isExist) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        } else {
            return ResultTool.success();
        }
    }

    /**
     * 上传图片
     *
     * @param req   后端使用的，前端无需考虑
     * @param image 文件
     * @return 返回值为图片的地址
     */
    @PostMapping("/uploadImg")
    @ApiOperation("上传图片")
    public JsonResult uploadImg(HttpServletRequest req, @RequestBody MultipartFile image) {
        StringBuilder url = new StringBuilder();
        String per = ".";
        String filePath = "/pic";
        //创建File类
        File imgFolder = new File(per + filePath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        //配置url,用于markdown编辑器的回显
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + Objects.requireNonNull(image.getOriginalFilename()).replaceAll(" ", "");

        try {
            //文件复制
            FileCopyUtils.copy(image.getBytes(), new File(imgFolder, imgName));
            url.append("/").append(imgName);
            return ResultTool.success(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultTool.fail(ResultCode.UPLOAD_ERROR);
    }


    /**
     * 验证密保答案
     *
     * @param answer   密保答案
     * @param username 用户名
     * @return 答案是否正确
     */
    @GetMapping("/verifyAnswer")
    @ApiOperation("验证密保答案")
    public JsonResult verifyAnswer(@NotBlank(message = "用户名不应为空") String username,
                                   @NotBlank(message = "密保答案不应为空") String answer) {
        try {
            Boolean verify = userLoginService.verifyAnswer(username, answer);
            if (verify) {
                return ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail(ResultCode.COMMON_FAIL, "验证失败");
    }

    /**
     * 更改密码
     *
     * @param password 新密码
     * @param username 用户名
     * @return 是否更改成功
     */
    @PostMapping("/changePassword")
    @ApiOperation("更改密码")
    public JsonResult changePassword(@NotBlank(message = "用户名不应为空") String username,
                                     @NotBlank(message = "新密码不应为空") String password) {
        try {
            Boolean change = userLoginService.changePassword(username, password);
            if (change) {
                return ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail(ResultCode.COMMON_FAIL, "更改失败");
    }

}
