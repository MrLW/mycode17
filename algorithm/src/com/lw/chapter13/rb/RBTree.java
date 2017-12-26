package com.lw.chapter13.rb;

/**
 * @author liwen
 * @date:2017年11月10日 下午2:43:55
 * @Function: 红黑数
 * @version 1.0
 */
public class RBTree<T extends Comparable<T>> {


    private static final boolean RED   = false;
    
    private static final boolean BLACK = true;
    
	// 根节点
	private RBTNode<T> root;

	public class RBTNode<T extends Comparable<T>> {
		// 颜色
		boolean color;
		// 关键字
		T key;
		// 左孩子
		RBTNode<T> left;
		// 右孩子
		RBTNode<T> right;
		// 父节点
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
	 * 对红黑树的节点(x)进行左旋转
	 *
	 * 左旋示意图(对节点x进行左旋)：
	 *      px                              px
	 *     /                               /
	 *    x                               y                
	 *   /  \      --(左旋)-.             / \                #
	 *  lx   y                          x  ry     
	 *     /   \                       /  \
	 *    ly   ry                     lx  ly  
	 *
	 *
	 */
	private void leftRotate(RBTNode<T> x) {
		// 设置x的右孩子为y
		RBTree<T>.RBTNode<T> y = x.right;
		// 将y的左节点给x的右节点,将x设置成y左节点的父节点
		// 主要是x和ly的关系
		x.right = y.left;
		if (x.left != null) {
			y.left.parent = x; 
		}
		// 设置y的父节点
		y.parent = x.parent ;
		if(x.parent == null) {
			this.root = y ;
		} else {
			if(x.parent.left == x ) {
				// 如果x是做节点
				x.parent.left = y ;
			} else {
				x.parent.right = y ;
			}
		}
		// 设置x为y的左节点
		y.left = x ;
		x.parent = y ;
	}
	/* 
	 * 对红黑树的节点(y)进行右旋转
	 *
	 * 右旋示意图(对节点y进行左旋)：
	 *            py                               py
	 *           /                                /
	 *          y                                x                  
	 *         /  \      --(右旋)-.            /  \                     #
	 *        x   ry                           lx   y  
	 *       / \                                   / \                   #
	 *      lx  rx                                rx  ry
	 * 
	 */
	private void rightRotate(RBTNode<T> y) {
		// 设置左孩子为x
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
	 * 将结点插入到红黑树中
	 *
	 * 参数说明：
	 *     node 插入的结点        // 对应《算法导论》中的node
	 */
	public void insert(RBTNode<T> node) {
		int cmp ;
		RBTNode<T> y = null ;
		RBTNode<T> x = this.root ;
		// 1、将红黑树当成二叉查找树
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
		
		// 2、设置节点颜色为红色
		node.color = RED ;
		  // 3. 将它重新修正为一颗二叉查找树
	    insertFixUp(node);
	}
	/*
	 * 红黑树插入修正函数
	 *
	 * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
	 * 目的是将它重新塑造成一颗红黑树。
	 *
	 * 参数说明：
	 *     node 插入的结点        // 对应《算法导论》中的z
	 */
	private void insertFixUp(RBTNode<T> node) {
	    RBTNode<T> parent, gparent;

	    // 若“父节点存在，并且父节点的颜色是红色”
	    while (((parent = parentOf(node))!=null) && isRed(parent)) {
	        gparent = parentOf(parent);

	        //若“父节点”是“祖父节点的左孩子”
	        if (parent == gparent.left) {
	            // Case 1条件：叔叔节点是红色
	            RBTNode<T> uncle = gparent.right;
	            if ((uncle!=null) && isRed(uncle)) {
	                setBlack(uncle);
	                setBlack(parent);
	                setRed(gparent);
	                node = gparent;
	                continue;
	            }

	            // Case 2条件：叔叔是黑色，且当前节点是右孩子
	            if (parent.right == node) {
	                RBTNode<T> tmp;
	                leftRotate(parent);
	                tmp = parent;
	                parent = node;
	                node = tmp;
	            }

	            // Case 3条件：叔叔是黑色，且当前节点是左孩子。
	            setBlack(parent);
	            setRed(gparent);
	            rightRotate(gparent);
	        } else {    //若“z的父节点”是“z的祖父节点的右孩子”
	            // Case 1条件：叔叔节点是红色
	            RBTNode<T> uncle = gparent.left;
	            if ((uncle!=null) && isRed(uncle)) {
	                setBlack(uncle);
	                setBlack(parent);
	                setRed(gparent);
	                node = gparent;
	                continue;
	            }

	            // Case 2条件：叔叔是黑色，且当前节点是左孩子
	            if (parent.left == node) {
	                RBTNode<T> tmp;
	                rightRotate(parent);
	                tmp = parent;
	                parent = node;
	                node = tmp;
	            }

	            // Case 3条件：叔叔是黑色，且当前节点是右孩子。
	            setBlack(parent);
	            setRed(gparent);
	            leftRotate(gparent);
	        }
	    }

	    // 将根节点设为黑色
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
	 * 新建结点(key)，并将其插入到红黑树中
	 *
	 * 参数说明：
	 *     key 插入结点的键值
	 */
	public void insert(T key) {
	    RBTNode<T> node=new RBTNode<T>(key,BLACK,null,null,null);

	    // 如果新建结点失败，则返回。
	    if (node != null)
	        insert(node);
	}
}
