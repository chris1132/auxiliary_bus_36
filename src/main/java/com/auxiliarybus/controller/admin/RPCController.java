package com.auxiliarybus.controller.admin;

import com.auxiliarybus.rpc.RpcImplement4Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangchaohui on 2019/1/25
 */
@RestController
public class RPCController {

    @Autowired
    public RpcImplement4Client rpcClient;

    @ResponseBody
    @RequestMapping(value = "/rpc", method = RequestMethod.GET)
    public String rpc(@RequestParam(value = "id", defaultValue = "1", required = false) int id) throws Exception {
        Object res = rpcClient.client4Async("com.auxiliarybus.service.impl.StudentServiceImpl", "getStudentById", id);
        return res.toString();
    }

}
