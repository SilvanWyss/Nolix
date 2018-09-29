//own import
import { List } from "./List";

//class
export class ListIterator<E> implements Iterator<E> {

    //attribute
    private listNode: ListNode<E>;

    //constructor
    constructor(listNode: ListNode<E>) {
        this.listNode = listNode;
    }

    //method
    next(): IteratorResult<E> {
    
        if (this.hasNext()) {
            let element = this.listNode.getRefElement();
            this.listNode = this.listNode.getRefNextNode();
            return { done: false, value: element };
        }

        return { done: true, value: null };
    }

    //method
    private hasNext(): boolean {
        return (this.listNode != null);
    }
}
