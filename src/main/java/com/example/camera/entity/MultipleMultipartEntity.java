/**
 * 
 */
package com.example.camera.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author deepakbisht
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleMultipartEntity {
	MultipartFile file1;
	MultipartFile file2;
}
