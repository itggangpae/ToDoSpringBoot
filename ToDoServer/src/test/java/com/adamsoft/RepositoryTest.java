package com.adamsoft;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.adamsoft.entity.ToDoEntity;
import com.adamsoft.persistency.ToDoRepository;

//스프링 부트의 테스트 클래스 라는 것을 설정
@SpringBootTest
public class RepositoryTest {
	//인스턴스가 있으면 자동 주입
	@Autowired
	ToDoRepository toDoRepository;

	//데이터 삽입 테스트
	//@Test
	public void testToDoInsert() {
		//삽입할 데이터 생성
		ToDoEntity entity = 
				ToDoEntity.builder()
				.userId("아담")
				.title("네번째 데이터")
				.done(true)
				.build();
		//데이터 삽입
		ToDoEntity result = 
				toDoRepository.save(entity);
		System.out.println(result);
	}

	//테이블의 데이터 전체 가져오기
	//@Test
	public void testToDoMany() {
		//테이블의 데이터 전체 가져오기
		/*
		List<ToDoEntity> list = 
				toDoRepository.findAll();
		 */

		//페이징
		//첫번째는 페이지 번호로 0부터 시작
		//두번째는 페이지 당 데이터 개수
		//Pageable pageable = PageRequest.of(0, 2);

		//title 의 내림차순 정렬
		Sort sort = Sort.by("title").descending();
		Pageable pageable = PageRequest.of(0, 2, sort);
		Page<ToDoEntity> list = 
				toDoRepository.findAll(pageable);
		for(ToDoEntity entity : list) {
			System.out.println(entity);
		}
	}

	//기본키를 이용한 데이터 1개 가져오기
	//@Test
	public void testFindOne() {
		//Optional 은 null 이 가능한 자료형
		Optional <ToDoEntity> optional = 
				toDoRepository.findById(
						"40288a8682aadc8d0182aadc95530000");
		//데이터가 존재하는 경우
		if(optional.isPresent()) {
			ToDoEntity entity = optional.get();
			System.out.println(entity);
		}
		//데이터가 존재하지 않는 경우
		else {
			System.out.println("데이터가 존재하지 않음");
		}
	}

	//데이터 수정 - save 메서드 이용
	//기본키 값이 있으면 수정
	//@Test
	public void testModify() {
		//수정할 데이터 생성
		ToDoEntity entity = 
				ToDoEntity.builder()
				.id("40288a8682aadc8d0182aadc95530000")
				.userId("adam")
				.title("수정된 데이터")
				.done(false)
				.build();
		//데이터 수정
		ToDoEntity result = 
				toDoRepository.save(entity);
		System.out.println(result);
	}
	
	//데이터 삭제
	//delete(Entity) 와 deleteById(id)
	//@Test
	public void testDelete() {
		toDoRepository.deleteById(
			"40288a8682aadc8d0182aadc95530000");
	}
	
	//@Test
	public void testNameMethod() {
		List<ToDoEntity> list = 
			toDoRepository.findByUserId("아담");
		System.out.println(list);
	}
}






