package com.test.blockchain.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.blockchain.entity.Block;

@RestController
public class BlockchainController {
	
	private static List<Block> blockList = new ArrayList<Block>();
	
	public BlockchainController() {
		blockList.add(createGenesisBlock());
	}
	
	private static Integer i = 0;
	
	@RequestMapping(value="/blockchain",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getBlockChains(){		
		Map<String,Object> blockMap = new HashMap<String,Object>();		
		blockMap.put("chain", blockList); 
    	blockMap.put("isvalidchain", isChainValid(blockList)? "Yes": "No");
    	return ResponseEntity.ok().body(blockMap);    		
	}
	
	@RequestMapping(value="/blockchain/add",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> addBlock(){		
		Map<String,Object> blockMap = new HashMap<String,Object>();		
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());		    	 
		blockList.add(addBlock(new Block(i.toString(),timeStamp.toString(),"Block "+i,null),blockList));    	
        i=i+1;
		blockMap.put("chain", blockList); 
    	blockMap.put("isvalidchain", isChainValid(blockList)? "Yes": "No");
    	return ResponseEntity.ok().body(blockMap);    		
	}
	
	@RequestMapping(value="/blockchain/delete",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> deleteBlock() {
		Map<String,Object> blockMap = new HashMap<String,Object>();
		if(blockList.size()>1)
		blockList.remove(blockList.size()-1);
		blockMap.put("chain", blockList); 
    	blockMap.put("isvalidchain", isChainValid(blockList)? "Yes": "No");
    	return ResponseEntity.ok().body(blockMap); 
	}
	
	@RequestMapping(value="/blockchain/count",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getBlockCount(){		
		Map<String,Object> blockMap = new HashMap<String,Object>();
		blockMap.put("count", blockList.size());
		return ResponseEntity.ok().body(blockMap);
	}
	
	public Block createGenesisBlock(){
	 Timestamp timeStamp = new Timestamp(System.currentTimeMillis());		
   	 return new Block("0",timeStamp.toString(),"Genesis Block","0");    	
    }
   
    public Block getLatestBlock(List<Block> blockList){
   	return blockList.get(blockList.size()-1);
    }
   
    public Block addBlock(Block newBlock,List<Block> blockList ){
   	newBlock.setPreviousHash(this.getLatestBlock(blockList).getHash());
   	newBlock.setHash(newBlock.calculateHash());
   	return newBlock;
    }
   
    public boolean isChainValid(List<Block> blockList) {    	
   	for(int i = 1; i<blockList.size();i++){    		
   		if(!blockList.get(i).getHash().equalsIgnoreCase(blockList.get(i).calculateHash())){
   			return false;
   		}
   		if(!blockList.get(i).getPreviousHash().equalsIgnoreCase(blockList.get(i-1).getHash())){
   			return false;
   		}
   	}
   	return true;
   }
}