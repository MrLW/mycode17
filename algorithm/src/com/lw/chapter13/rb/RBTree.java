package com.lw.chapter13.rb;

/**
 * @author liwen
 * @date:2017��11��10�� ����2:43:55
 * @Function: �����
 * @version 1.0
 */
public class RBTree<T extends Comparable<T>> {


    private static final boolean RED   = false;
    
    private static final boolean BLACK = true;
    
	// ���ڵ�
	private RBTNode<T> root;

	public class RBTNode<T extends Comparable<T>> {
		// ��ɫ
		boolean color;
		// �ؼ���
		T key;
		// ����
		RBTNode<T> left;
		// �Һ���
		RBTNode<T> right;
		// ���ڵ�
		RBTNode<T> parent;

		public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
			this.key = key;
			this.color = color;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

	}
	/* 
	 * �Ժ�����Ľڵ�(x)��������ת
	 *
	 * ����ʾ��ͼ(�Խڵ�x��������)��
	 *      px                              px
	 *     /                               /
	 *    x                               y                
	 *   /  \      --(����)-.             / \                #
	 *  lx   y                          x  ry     
	 *     /   \                       /  \
	 *    ly   ry                     lx  ly  
	 *
	 *
	 */
	private void leftRotate(RBTNode<T> x) {
		// ����x���Һ���Ϊy
		RBTree<T>.RBTNode<T> y = x.right;
		// ��y����ڵ��x���ҽڵ�,��x���ó�y��ڵ�ĸ��ڵ�
		// ��Ҫ��x��ly�Ĺ�ϵ
		x.right = y.left;
		if (x.left != null) {
			y.left.parent = x; 
		}
		// ����y�ĸ��ڵ�
		y.parent = x.parent ;
		if(x.parent == null) {
			this.root = y ;
		} else {
			if(x.parent.left == x ) {
				// ���x�����ڵ�
				x.parent.left = y ;
			} else {
				x.parent.right = y ;
			}
		}
		// ����xΪy����ڵ�
		y.left = x ;
		x.parent = y ;
	}
	/* 
	 * �Ժ�����Ľڵ�(y)��������ת
	 *
	 * ����ʾ��ͼ(�Խڵ�y��������)��
	 *            py                               py
	 *           /                                /
	 *          y                                x                  
	 *         /  \      --(����)-.            /  \                     #
	 *        x   ry                           lx   y  
	 *       / \                                   / \                   #
	 *      lx  rx                                rx  ry
	 * 
	 */
	private void rightRotate(RBTNode<T> y) {
		// ��������Ϊx
		RBTNode<T> x = y.left ;
		y.left = x.right;
		if(x.right != null ) {
			x.right.parent = y ;
		}
		
		x.parent = y.parent ;
		if(y.parent == null ) {
			this.root = x ;
		} else {
			if(y.parent.left == y ) {
				y.parent.left = x; 
			} else {
				y.parent.right = y ;
			}
		}
		x.right = y ;
		y.parent = x; 
	}
	
	/* 
	 * �������뵽�������
	 *
	 * ����˵����
	 *     node ����Ľ��        // ��Ӧ���㷨���ۡ��е�node
	 */
	public void insert(RBTNode<T> node) {
		int cmp ;
		RBTNode<T> y = null ;
		RBTNode<T> x = this.root ;
		// 1������������ɶ��������
		while( x != null ) {
			y = x; 
			cmp = node.key.compareTo(x.key) ;
			if(cmp < 0 )
				x = x.left ;
			else 
				x = x.right ;
		}
		node.parent = y ;
		if (y!=null) {
	        cmp = node.key.compareTo(y.key);
	        if (cmp < 0)
	            y.left = node;
	        else
	            y.right = node;
	    } else {
	        this.root = node;
	    }
		
		// 2�����ýڵ���ɫΪ��ɫ
		node.color = RED ;
		  // 3. ������������Ϊһ�Ŷ��������
	    insertFixUp(node);
	}
	/*
	 * �����������������
	 *
	 * ���������в���ڵ�֮��(ʧȥƽ��)���ٵ��øú�����
	 * Ŀ���ǽ������������һ�ź������
	 *
	 * ����˵����
	 *     node ����Ľ��        // ��Ӧ���㷨���ۡ��е�z
	 */
	private void insertFixUp(RBTNode<T> node) {
	    RBTNode<T> parent, gparent;

	    // �������ڵ���ڣ����Ҹ��ڵ����ɫ�Ǻ�ɫ��
	    while (((parent = parentOf(node))!=null) && isRed(parent)) {
	        gparent = parentOf(parent);

	        //�������ڵ㡱�ǡ��游�ڵ�����ӡ�
	        if (parent == gparent.left) {
	            // Case 1����������ڵ��Ǻ�ɫ
	            RBTNode<T> uncle = gparent.right;
	            if ((uncle!=null) && isRed(uncle)) {
	                setBlack(uncle);
	                setBlack(parent);
	                setRed(gparent);
	                node = gparent;
	                continue;
	            }

	            // Case 2�����������Ǻ�ɫ���ҵ�ǰ�ڵ����Һ���
	            if (parent.right == node) {
	                RBTNode<T> tmp;
	                leftRotate(parent);
	                tmp = parent;
	                parent = node;
	                node = tmp;
	            }

	            // Case 3�����������Ǻ�ɫ���ҵ�ǰ�ڵ������ӡ�
	            setBlack(parent);
	            setRed(gparent);
	            rightRotate(gparent);
	        } else {    //����z�ĸ��ڵ㡱�ǡ�z���游�ڵ���Һ��ӡ�
	            // Case 1����������ڵ��Ǻ�ɫ
	            RBTNode<T> uncle = gparent.left;
	            if ((uncle!=null) && isRed(uncle)) {
	                setBlack(uncle);
	                setBlack(parent);
	                setRed(gparent);
	                node = gparent;
	                continue;
	            }

	            // Case 2�����������Ǻ�ɫ���ҵ�ǰ�ڵ�������
	            if (parent.left == node) {
	                RBTNode<T> tmp;
	                rightRotate(parent);
	                tmp = parent;
	                parent = node;
	                node = tmp;
	            }

	            // Case 3�����������Ǻ�ɫ���ҵ�ǰ�ڵ����Һ��ӡ�
	            setBlack(parent);
	            setRed(gparent);
	            leftRotate(gparent);
	        }
	    }

	    // �����ڵ���Ϊ��ɫ
	    setBlack(this.root);
	}
	 private void setBlack(RBTNode<T> node) {
	      if (node!=null)
	          node.color = BLACK;
	 }
	 
	 private void setRed(RBTNode<T> node) {
	    if (node!=null)
	        node.color = RED;
	 }
	 private RBTNode<T> parentOf(RBTNode<T> node) {
        return node!=null ? node.parent : null;
     }
	 private boolean isRed(RBTNode<T> node) {
		  return ((node!=null)&&(node.color==RED)) ? true : false;
	 }
	/* 
	 * �½����(key)����������뵽�������
	 *
	 * ����˵����
	 *     key ������ļ�ֵ
	 */
	public void insert(T key) {
	    RBTNode<T> node=new RBTNode<T>(key,BLACK,null,null,null);

	    // ����½����ʧ�ܣ��򷵻ء�
	    if (node != null)
	        insert(node);
	}
}
