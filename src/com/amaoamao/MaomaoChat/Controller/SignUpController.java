/*
 * Copyright (c) 2017 Peter Mao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amaoamao.MaomaoChat.Controller;


import com.amaoamao.MaomaoChat.Entity.Error;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/signup")
public class SignUpController {

    private final TaobaoClient client;
    private Pattern phonePattern = Pattern.compile("[0-9]{11}");

    @Autowired
    public SignUpController(TaobaoClient client) {
        this.client = client;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public PhoneAuthResult phoneAuth(@RequestParam("phone") String phone, @RequestParam(value = "code", required = false) String code) throws ApiException {
        PhoneAuthResult result = new PhoneAuthResult();
        if (phonePattern.matcher(phone).matches()) {
            if (code == null) {
//                AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//                req.setSmsFreeSignName("Puppic团队");
//                req.setSmsTemplateCode("SMS_65910140");
//                req.setSmsType("normal");
//                req.setSmsParamString("{number:'" + Helper.RandomString(6) + "',phone:'" + phone + "'}");
//                req.setRecNum(phone);
//                AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
//                System.out.println(new Gson().toJson(rsp));
//                if (rsp.getResult().getErrCode().equals("0")) {
                result.setError(new Error(0, "发送成功"));
//                } else {
//                    result.setError(new Error(1, "发送失败"));
//                }
            } else {
                result.setError(new Error(0, "验证成功"));
                result.setToken(new Token(phone, "nsdjcvkndskvncks"));
            }
        } else {
            result.setError(new Error(1, "参数非法"));
        }
        return result;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public PhoneAuthResult signUp(@RequestBody SignUpParam param) {
        System.out.println(new Gson().toJson(param.account));
        System.out.println(new Gson().toJson(param.token));
        return new PhoneAuthResult(new Error(0, "注册成功"), null);
    }

    static class SignUpParam {

        @SerializedName("account")
        @Expose
        private Account account;
        @SerializedName("token")
        @Expose
        private Token token;

        public SignUpParam() {
        }

        public SignUpParam(Account account, Token token) {
            super();
            this.account = account;
            this.token = token;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public Token getToken() {
            return token;
        }

        public void setToken(Token token) {
            this.token = token;
        }

    }

    static class PhoneAuthResult {
        @SerializedName("error")
        @Expose
        private Error error;
        @SerializedName("token")
        @Expose
        private Token token;

        public PhoneAuthResult() {
        }

        public PhoneAuthResult(Error error, Token token) {
            super();
            this.error = error;
            this.token = token;
        }

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
        }

        public Token getToken() {
            return token;
        }

        public void setToken(Token token) {
            this.token = token;
        }

    }

    static class Token {

        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("token")
        @Expose
        private String token;

        public Token() {
        }

        public Token(String phone, String token) {
            super();
            this.phone = phone;
            this.token = token;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    }

    static class Account {
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("name")
        @Expose
        private String name;

        public Account() {
        }

        public Account(String phone, String password, String name) {
            super();
            this.phone = phone;
            this.password = password;
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
