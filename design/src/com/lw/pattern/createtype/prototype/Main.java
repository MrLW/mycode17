package com.lw.pattern.createtype.prototype;

/**
 * @author liwen
 * @date:2017��12��4�� ����10:18:49
 * @Function: ע��:ʹ��ԭ��ģʽ���������Ĺ��캯��,������͵����ǳ�ͻ��,��Ϊ�������ӹ��캯����
 *            �漰��������:Cloneable,ǳ����/�����ǳ��������������(copy�����������޸��˻ᵼ��srcҲ���޸�,��Ϊָ��ͬһ������).
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		ConcretePrototype prototype1 = new ConcretePrototype();
		prototype1.name = "lw"; 
		for (int i = 0; i < 10; i++) {
			ConcretePrototype clone = (ConcretePrototype) prototype1.clone();
			System.out.println(clone.list);
			System.out.println(clone.name);
		}

	}
}
