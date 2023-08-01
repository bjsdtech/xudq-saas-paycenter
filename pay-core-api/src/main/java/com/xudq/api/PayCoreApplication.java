package com.xudq.api;

import com.xudq.api.po.AcmAccountDto;
import com.xudq.api.vo.BaseResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xudq.api.dao"})
public class PayCoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(PayCoreApplication.class, args);
	}
}
