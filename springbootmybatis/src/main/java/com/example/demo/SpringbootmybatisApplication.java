package com.example.demo;

import com.example.demo.dao.CityMapper;
import com.example.demo.entity.City;
import com.example.demo.entity.CityExample;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.management.VMManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

@SpringBootApplication
public class SpringbootmybatisApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmybatisApplication.class, args);
	}

//	@Autowired
//	private CityMapper cityMapper;

	@Override
	public void run(String... strings) throws Exception {
		VirtualMachine virtualMachine = VirtualMachine.attach(getProcessID() + "");
		List<VirtualMachineDescriptor> machineDescriptorList = VirtualMachine.list();
		for (VirtualMachineDescriptor machineDescriptor : machineDescriptorList) {
			String id = machineDescriptor.id();
			System.out.println(id);
		}

		String[] split = "0".split(";");
		System.out.println(split);

	}

	public static final int getProcessID() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		System.out.println(runtimeMXBean.getName());
		return Integer.valueOf(runtimeMXBean.getName().split("@")[0])
				.intValue();
	}
}
