package com.lw.jibx;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JibxTest {

	private IBindingFactory factory = null;
	private StringWriter writer;
	private StringReader reader;

	private Account account;

	@Before
	public void init() {
		account = new Account();
		account.setAddress("北京");
		account.setEmail("email");
		account.setId(1);
		account.setName("jack");
		Birthday day = new Birthday();
		day.setBirthday("2010-11-22");
		account.setBirthday(day);
		try {
			factory = BindingDirectory.getFactory(Account.class);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bean2XML() {
		writer = new StringWriter() ;
		try {
			// marshal 编排
			IMarshallingContext mctx = factory.createMarshallingContext() ;
			mctx.setIndent(2);
			mctx.marshalDocument(account, "utf-8", null, writer);
			fail(writer);
			reader = new StringReader(writer.toString());
			// unmarshal 分解 
			IUnmarshallingContext uctx = factory.createUnmarshallingContext() ;
			Account account = (Account) uctx.unmarshalDocument(reader, null);
			fail(account);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		
	}
	 public void fail(Object o) {
	        System.out.println(o);
	    }
	@After
	public void destory() {
		account = null;
		try {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
