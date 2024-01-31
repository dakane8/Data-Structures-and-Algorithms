package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    boolean hasLeftChild(int index) {
        return(validIndex(leftChildInd(index)));}

    boolean hasRightChild(int index) {
        return(validIndex(rightChildInd(index)));}

    static int leftChildInd(int index) {
        return (2*index+1);}

    static int rightChildInd(int index) {
        return (2*index+2);}

    static int parentInd(int index) {
        return ((index-1)/2);}

    boolean validIndex(int index) {
        return (index >= 0) && (index <= _heap.size() - 1);
    }
    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Part 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        _heap.addAll(Arrays.asList(initialEntries));
        moveup(_heap.size()-1);

    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Prioritized<V,P>  pat = new Patient<>(value, priority);
        _heap.add(_heap.size(), pat);
        moveup(_heap.size()-1);
    }
    void moveup(int index) {
        Prioritized<V, P> child = _heap.get(index);
        Prioritized<V, P> parent = _heap.get(parentInd(index));
        if (child.getPriority().compareTo(parent.getPriority()) > 0) {
            _heap.set(parentInd(index), child);
            _heap.set(index, parent);
            moveup(parentInd(index));
        }
    }
    void movedown(int index) {
        Prioritized<V, P> parent = _heap.get(index);
        if (!hasLeftChild(index) && !hasRightChild(index)) {
            return;
        } else if (!hasRightChild(index)) {
            Prioritized<V, P> leftChild = _heap.get(leftChildInd(index));
            if (leftChild.getPriority().compareTo(parent.getPriority()) > 0) {
                _heap.set(index, leftChild);
                _heap.set(leftChildInd(index), parent);
                movedown(leftChildInd(index));
            } else {
                return;
            }
        }
            else{
                Prioritized<V, P> leftChild = _heap.get(leftChildInd(index));
                Prioritized<V, P> rightChild = _heap.get(rightChildInd(index));
                if ((leftChild.getPriority().compareTo(parent.getPriority()) > 0) || (rightChild.getPriority().compareTo(parent.getPriority()) > 0)) {
                    if (leftChild.getPriority().compareTo(rightChild.getPriority()) > 0) {
                        _heap.set(index, leftChild);
                        _heap.set(leftChildInd(index), parent);
                        movedown(leftChildInd(index));
                    } else {
                        _heap.set(index, rightChild);
                        _heap.set(rightChildInd(index), parent);
                        movedown(rightChildInd(index));
                    }
                } else {
                    return;
                }
            }
        }

    // TODO: enqueue
    public void enqueue(V value) {
            Prioritized<V, P> pat = new Patient<>(value);
            _heap.add(_heap.size(), pat);
            moveup(_heap.size()-1);
        }


    // TODO: dequeue
    @Override
    public V dequeue() {

        if (_heap.size() == 0) {
            return null;
        }
        else {
            V retValue = _heap.get(0).getValue();
            if (_heap.size() == 1) {
                _heap.remove(0);
            }
            else {
                _heap.set(0, _heap.get(_heap.size()-1));
                _heap.remove(_heap.size()-1);
                movedown(0);
            }
            return retValue;
        }
    }

    // TODO: getMax
    @Override
    public V getMax() {
        if(_heap.isEmpty()) {
            return null;
        }
        else {
            return _heap.get(0).getValue();
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }


}
