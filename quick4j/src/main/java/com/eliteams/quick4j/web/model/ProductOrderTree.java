package com.eliteams.quick4j.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 开始实现树，提供基本的插入节点、获取所有节点、获取树的深度操作（着重）
 * 这些将数组大小设置为2，主要是考虑数组能否自动扩容
 * @author qianjun
 *
 */
public class ProductOrderTree {
	
	private final int DEFAULT_SIZE = 7;  
    private int size;  
    private int count;  
    private Object[] nodes;
    
	public ProductOrderTree() {
		super();
	}
    
    public ProductOrderTree(Node root){
    	this();  
        this.count = 1;  
        this.nodes[0] = root; 
    }
    
    public ProductOrderTree(Node root, int size) {  
        this.size = size;  
        this.nodes = new Object[this.size];  
        this.count = 1;  
        this.nodes[0] = root;  
    }
    
    //添加一个节点  
    public void add(Node node) {  
        for (int i = 0; i < this.size; i++) {  
            if (this.nodes[i] == null) {  
                nodes[i] = node;  
                break;  
            }  
        }  
        this.count++;  
    }  
  
    public void check(){  
        if(this.count >= this.size){  
            this.enlarge();  
        }  
    }  
    //添加一个节点，并指明父节点  
    public void add(Node node, Node parent) {  
        this.check();  
        node.setParent(this.position(parent));  
        this.add(node);  
    }  
  
    //获取节点在数组的存储位置  
    public int position(Node node) {  
        for (int i = 0; i < this.size; i++) {  
            if (nodes[i] == node) {  
                return i;  
            }  
        }  
        return -1;  
    }  
      
    //获取整棵树有多少节点  
    public int getSize(){  
        return this.count;  
    }  
      
    //获取根节点  
    @SuppressWarnings("unchecked")  
    public Node getRoot(){  
        return (Node) this.nodes[0];  
    }  
      
    //获取所有节点，以List形式返回  
    @SuppressWarnings("unchecked")  
    public List<Node> getAllNodes(){  
        List<Node> list = new ArrayList<Node>();  
        for(int i=0;i<this.size;i++){  
            if(this.nodes[i] != null){  
                list.add((Node)nodes[i]);  
            }  
        }  
        return list;  
    }  
      
    //获取树的深度，只有根节点时为1  
    @SuppressWarnings("unchecked")  
    public int getDepth(){  
          
        int max = 1;  
        if(this.nodes[0] == null){  
            return 0;  
        }  
          
        for(int i=0;i<this.count;i++){  
            int deep = 1;  
            int location = ((Node)(this.nodes[i])).getParent();  
            while(location != -1 && this.nodes[location] != null){  
                location = ((Node)(this.nodes[location])).getParent();  
                deep++;  
            }  
            if(max < deep){  
                max = deep;  
            }  
        }  
        return max;  
    }  
      
    public void enlarge(){  
        this.size = this.size + this.DEFAULT_SIZE;  
        Object[] newNodes = new Object[this.size];  
        newNodes = Arrays.copyOf(nodes, this.size);  
        Arrays.fill(nodes, null);  
        this.nodes = newNodes;  
        System.out.println("enlarge");  
    }  

}
