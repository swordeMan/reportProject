package com.eliteams.quick4j.web.model;

/**
 * 定义一个节点Node类，用来存储每个节点的内容
 * @author qianjun
 *
 */
public class Node {
	
	private OrderNode orderNode;
	private int parent;
	
	public Node() {
		super();
	}

	public Node(OrderNode orderNode){
		this.orderNode = orderNode;
	}
	
	public Node(OrderNode orderNode, int parent) {
		super();
		this.orderNode = orderNode;
		this.parent = parent;
	}
	
	public OrderNode getOrderNode() {
		return orderNode;
	}

	public void setOrderNode(OrderNode orderNode) {
		this.orderNode = orderNode;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

}
