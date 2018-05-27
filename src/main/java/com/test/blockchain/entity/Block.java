package com.test.blockchain.entity;
import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Block {

	private String index;
	private String timestamp;
	private String data;
	private String previousHash;
	private String hash;
	
	public Block(String index, String timstamp,String data, String previousHash) {
	  this.index = index;
	  this.timestamp=timstamp;
	  this.data = data;
	  this.previousHash = previousHash;
	  this.hash = calculateHash();
	}

	public String calculateHash(){		
		CharSequence inputString = this.index+this.previousHash+this.timestamp+this.data; 
		String hash = Hashing.sha256().hashString(inputString, StandardCharsets.UTF_8).toString();
		return hash;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
