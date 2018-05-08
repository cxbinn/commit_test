package com.skywalker.basisSchedule.scheduleController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skywalker.basisSchedule.scheduleService.ScheduleInitService;
import com.skywalker.basisSchedule.scheduleService.ScheduleRepairService;
import com.skywalker.basisSchedule.scheduleService.ScheduleService;
import com.skywalker.common.service.ScheduleCommonService;
import com.skywalker.utils.CommonUtil;
import com.skywalker.utils.Constants;


@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private ScheduleInitService scheduleInitService;
	
	@Autowired
	private ScheduleRepairService scheduleRepairService;
	
	@Autowired
	private ScheduleCommonService scheduleCommonService;
	
	@RequestMapping(value="/Schedule/scheduleInit.do")
	public void scheduleInit(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jresult=new JSONObject();
			int result=scheduleInitService.init();
			if(result==Constants.FAIL){
				jresult.put("result",result);
			}else{
				jresult.put("result",scheduleInitService.init());
			}
			CommonUtil.renderData(response, jresult);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
