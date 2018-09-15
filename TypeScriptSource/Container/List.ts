//reference
/// <reference path="ListNode.ts" />

//namespace declaration
namespace Nolix.List {

    //cass
    export class List<E> {

        //attribute
        private elementCount: number = 0;

        //optional attributes
        private beginNode: ListNode<E>;
        private endNode: ListNode<E>;

        //method
        public addAtBegin(element: E): List<E> {

            const previousNode = new ListNode<E>(element);

            if (this.isEmpty) {
                this.beginNode = previousNode;
                this.endNode = previousNode;
            }
            else {
                previousNode.setNextNode(this.beginNode);
                this.beginNode = previousNode;
            }

            this.elementCount++;

            return this;
        }

        //method
        public addAtEnd(element: E): List<E> {

            const nextNode = new ListNode<E>(element);

            if (this.isEmpty) {
                this.beginNode = nextNode;
                this.endNode = nextNode;
            }
            else {
                this.endNode.setNextNode(nextNode);
                this.endNode = nextNode;
            }

            this.elementCount++;

            return this;
        }

        //method
        public containsAnyElement() {
            return (this.getElementCount() > 0);
        }

        //method
        public getElementCount(): number {
            return this.elementCount;
        }

        //method
        public getRefFirstElement(): E {
            
            this.supposeIsNotEmpty();

            return this.beginNode.getRefElement();
        }

        //method
        public getRefLastElement(): E {

            this.supposeIsNotEmpty();

            return this.endNode.getRefElement();
        }

        //method
        public isEmpty() {
            return (this.getElementCount() == 0);
        }

        //method
        private supposeIsNotEmpty() {
            if (this.isEmpty) {
                throw new Error("The current list is empty.");
            }
        }
    }
}
