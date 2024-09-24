package com.jhmemo.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	// 상수
	public static final String FILE_UPLOAD_PATH = "D:\\jh\\spring\\springProject\\upload\\memo";
	
	// 파일 저장
	public static String saveFile(int userId, MultipartFile file)
	{
		if(file == null)
		{
			return null;
		}
		// 같은 파일이름으로 전달 될 경우 
		// 폴더를 만들어서 파일 저장
		// 로그인 사용자 userId를 폴더 이름으로 사용
		// 현재 시간정보를 폴더 이름으로 사용
		// UNIX TIME : 1970년 1월 1일부터 흐른 시간을 milli second(1/1000)로 표현한 값
		// ex) 2_93101203124
		
		// 윈도우에서는 \로 파일 위치 구분하지만 시스템내에서는 /로 파일 위치 구분함
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 폴더 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		// mkdir = makedirectory의 약자
		if(!directory.mkdir())
		{
			// 폴더 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		byte[] bytes;
		
		try {
			bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
		} catch (IOException e) {
			// 파일 저장 실패
			return null;
		}
		
		// 저장된 파일을 접근할 URL경로를 만들기
		// 파일 저장 경로 : D:\\jh\\spring\\springProject\\upload\\memo\2_8123124\test.png
		// URL path : /images/2_8123124/test.png
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	
	// 폴더 안의 파일 삭제 함수
	public static boolean removeFile(String filePath)
	{
		if(filePath == null)
		{
			return false;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(fullFilePath);
		Path directoryPath = path.getParent();
		
		// 파일이 존재하는지 확인
		if(Files.exists(path) && Files.exists(directoryPath))
		{
			try {
				Files.delete(path);
				Files.delete(directoryPath);
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		else
		{
			return false;
		}
		
		/*	// 폴더와 디렉토리 분리해서 파일과 디렉토리를 분리하여 제거하는 방식
		// 폴더(디렉토리) 제거
		path = path.getParent();	// 상위경로 리턴
		
		if(Files.exists(path))
		{
			try {
				Files.delete(path);
			} catch (IOException e) {
				return false;
			}
		}
		
		return true;
		*/
	}
}
