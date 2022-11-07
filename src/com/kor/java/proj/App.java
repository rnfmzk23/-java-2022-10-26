package com.kor.java.proj;

import java.util.Scanner;

import com.kor.java.proj.controller.ArticleController;
import com.kor.java.proj.controller.Controller;
import com.kor.java.proj.controller.MemberController;

public class App {

	public void start() {
		System.out.println("== 프로그램 시작 ==");


		Scanner sc = new Scanner(System.in);
		
		MemberController membercontroller = new MemberController(sc);
		ArticleController articlecontroller = new ArticleController(sc);
		
		articlecontroller.makeTestData();

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			command = command.trim();

			if (command.length() == 0) {
				continue;
			}
			if (command.equals("system exit")) {
				break;
			} 
			
			command.split(" ");
			String[] commandBits = command.split(" ");
			
			if(commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
			}
			
			String controllerName = commandBits[0];
			String actionName = commandBits[1];
			
			Controller controller = null;
			
			if(controllerName.equals("article")) {
				controller = articlecontroller;
			} else if(controllerName.equals("member")) {
				controller = membercontroller;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			controller.doAction(command, actionName);
						
		}

		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
	

	
}
