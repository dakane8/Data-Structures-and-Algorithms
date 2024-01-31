package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateLeft() {
         AVLTree<T> newRoot = _right;
         AVLTree<T> zz = newRoot._left;

         newRoot._left = this;
         _right = zz;
         _height = Math.max(_left.height(), _right.height())+1;
         _size = _left._size + _right._size + 1;

         newRoot._height = Math.max(newRoot._left.height(), newRoot._right.height())+1;
         newRoot._size = newRoot._left._size + newRoot._right._size + 1;

         return newRoot;
     }
    
    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
         AVLTree<T> newRoot = _left;
         AVLTree<T> zz = newRoot._right;

         newRoot._right = this;
         _left = zz;
         _height = Math.max(_left.height(), _right.height())+1;
         _size = _left._size + _right._size + 1;

         newRoot._height = Math.max(newRoot._left.height(), newRoot._right.height())+1;
         newRoot._size = newRoot._left._size + newRoot._right._size + 1;

         return newRoot;
     }
    public void updateHeightAndSize() {
        int leftHeight = (_left == null) ? -1 : _left._height;
        int rightHeight = (_right == null) ? -1 : _right._height;

        _height = 1 + Math.max(leftHeight, rightHeight);
        _size = 1 + getSize(_left) + getSize(_right);
    }
    public int getSize(AVLTree<T> tree) {
        return (tree == null) ? 0 : tree._size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    public AVLTree<T> balance() {
        _height = Math.max(_left.height(), _right.height())+1;
        int balance = balanceFactor();
        if(balance < -1) {
            if (_right.balanceFactor()   > 0) {
                _right = this._right.rotateRight();
            }
            return this.rotateLeft();
        }
        if(balance > 1) {
            if (_left.balanceFactor() < 0) {
                _left = _left.rotateLeft();
            }
            return this.rotateRight();
        }
        return this;
    }
    private int balanceFactor(){
        if(isEmpty()){
            return 0;
        }
        return _left.height() - _right.height();
    }
    @Override
    public SelfBalancingBST<T> insert(T element) {
        if(isEmpty()) {
            _value = element;
            _left = new AVLTree<T>();
            _right = new AVLTree<T>();
            _size++;
            _height = Math.max(_left.height(), _right.height()) + 1;
        }
        else if(element.compareTo(getValue()) >= 0) {
            _right = (AVLTree<T>) _right.insert(element);
            _height = Math.max(_left.height(), _right.height()) + 1;
            _size++;
        }
        else{
            _left = (AVLTree<T>) _left.insert(element);
            _height = Math.max(_left.height(), _right.height()) + 1;
            _size++;
        }

        return balance();
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (isEmpty()) {
            return this;
        }

        if (element.compareTo(_value) == 0) {
            if (_left.isEmpty() && _right.isEmpty()) {
                return new AVLTree<T>();
            } else if (_left.isEmpty()) {
                return _right;
            } else if (_right.isEmpty()) {
                return _left;
            } else {
                T next = _right.findMin();
                _right = (AVLTree<T>) _right.remove(next);
                _value = next;
            }
        }
        else if(element.compareTo(_value) < 0){
            _left = (AVLTree<T>) _left.remove(element);
        }
        else if(element.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.remove(element);
        }

        _height = Math.max(_left.height(), _right.height()) + 1;
        _size = _left._size + _right._size + 1;

        return balance();
    }

    @Override
    public T findMin() {
         if (isEmpty()) {
             throw new RuntimeException("Illegal operation on empty tree");
         }
         if (_left.isEmpty()) {
             return _value;
         } else {
             return _left.findMin();
         }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
        if(isEmpty()) {
            return false;
        }
        else if(_value.compareTo(element) == 0){
            return true;
        }
        else if(_value.compareTo(element) > 0) {
            return _left.contains(element);
        }
        else {
            return _right.contains(element);
        }
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
         return _right;
    }

}
