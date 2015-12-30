package cn.cttic.sysframe.common.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EasyTreeModel {

	public enum NodeState {
		open, closed;
	}

	private String id;
	private String text;
	private String parentId;
	private String iconCls = "icon-blank";
	private NodeState state = NodeState.open;
	private Boolean checked = false;
	private Map<String, Object> attributes = new LinkedHashMap<String, Object>(0);
	private List<EasyTreeModel> children = new LinkedList<EasyTreeModel>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public NodeState getState() {
		return state;
	}

	public void setState(NodeState state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	public void addAttribute(String key, Object value) {
		this.attributes.put(key, value);
	}
	
	public void addAttributeList(Map<String, Object> attributes) {
		this.attributes.putAll(attributes);
	}

	public List<EasyTreeModel> getChildren() {
		return children;
	}
	
	public void addChildren(EasyTreeModel childre) {
		this.children.add(childre);
	}
	
	public void addChildrenList(List<EasyTreeModel> children) {
		this.children.addAll(children);
	}

}
