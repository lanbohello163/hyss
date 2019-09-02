package com.zxtech.platform.property.exception;
/**
 * 不能添加属性到数据库配置表中的费运行时异常
 * 
 * @version 1.0.0
 */
public class CannotAddPropertyException extends Exception
{
	private static final long serialVersionUID = 8613833442284598705L;

	public CannotAddPropertyException()
	{
		super();
	}

	public CannotAddPropertyException(String msg)
	{
		super(msg);
	}

	public CannotAddPropertyException(String msg, Throwable e)
	{
		super(msg, e);
	}
}
