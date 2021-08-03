package com.firstmeridian.ipmgmt.resource;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;//added
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;//added
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.service.IpPoolService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = IpManagementController.class)
@WithMockUser
class IpManagementControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IpPoolService IpPoolService;
	Optional<List<IpPoolEntity>> mockipPools = Optional.of(Arrays.asList(new IpPoolEntity("desc1", 20, 10, 0, 5)));
	@Test
	void findAllTest() throws Exception {
		//fail("Not yet implemented");
		System.out.println("test executed");
		Mockito.when(IpPoolService.ipPools()).thenReturn(mockipPools);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ipmgmt/ippools");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());

	}

}
