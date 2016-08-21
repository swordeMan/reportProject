package com.eliteams.quick4j.core.generic;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.eliteams.quick4j.core.editor.DateEditor;
import com.eliteams.quick4j.core.editor.DoubleEditor;
import com.eliteams.quick4j.core.editor.IntegerEditor;
import com.eliteams.quick4j.core.editor.LongEditor;
import com.eliteams.quick4j.core.entity.Json;

public abstract class GenericController {
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
}
