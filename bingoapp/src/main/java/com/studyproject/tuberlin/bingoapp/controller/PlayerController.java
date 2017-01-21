package com.studyproject.tuberlin.bingoapp.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studyproject.tuberlin.bingoapp.entity.Player;
import com.studyproject.tuberlin.bingoapp.service.PlayerService;

/**
 * This class is a controller for Player entity (Table)
 * @author sudarson
 *
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

	PlayerService playerService;
	
	@Autowired
	   public PlayerController(PlayerService playerService) {
			this.playerService = playerService;
		}
	
	@RequestMapping(value = "/bingodb/dummy/")
	public ResponseEntity<Player> get() {

	    Player player = new Player();
	    player.setEmail("ss@gmail.com");
	    player.setAge("56");
	    player.setGender("Male");
	    return new ResponseEntity<Player>(player, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bingodb/player/registration/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Player> registerPlayer(@RequestBody Player player) throws Exception{
		Player savedPlayer = playerService.registerPlayer(player);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(player.getProfilePicture()));
		ImageIO.write(image, "jpeg", new File("images"+File.separator+savedPlayer.getPlayerId()+".jpeg"));
		return new ResponseEntity<Player> (savedPlayer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bingodb/profile_photo/{playerId}", method = RequestMethod.GET, produces="image/jpeg")
	public @ResponseBody byte[] getPlayerProfilePicture(@PathVariable String playerId){
		  /*Player p = playerService.getPlayerProfilePicture(playerId);
		  byte[] pic = p.getProfilePicture();*/
		try{
			Path currentRelativePath = Paths.get("");
			String directoryPath = currentRelativePath.toAbsolutePath().toString();
			InputStream inputStream = new FileInputStream(directoryPath+File.separator+"images"+File.separator+playerId+".jpeg");
			BufferedImage image =  ImageIO.read(inputStream);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(image, "jpeg", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		}catch(Exception e){
			System.out.println("Exception >>>>>>"+e);
			throw new RuntimeException(e);
		}
	}
}
