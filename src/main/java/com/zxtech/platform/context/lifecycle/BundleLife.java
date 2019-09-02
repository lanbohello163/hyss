package com.zxtech.platform.context.lifecycle;
/**
 * 每个组件中都应该在包的根目录下包含一个实现了该接口的类。这个类，会在syscontext组件启动过程中被调用，以便对各个组件需要初始化处理进行操作。
 * 
 * @author 
 * @version 1.0.0
 */
public interface BundleLife
{
	public void init();
	public void destroy();
}