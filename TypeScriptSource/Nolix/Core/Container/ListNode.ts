//namespace declaration
namespace Nolix.Core.Container {

    //class
    export class ListNode<E> {

        //attribute
        private element: E;

        //optional attribute
        private nextNode: ListNode<E>;

        //constructor
        constructor(element: E) {

            if (element == null) {
                throw new Error("The given element is null.");
            }
            
            this.element = element;
        }

        //method
        public getRefElement(): E {
            return this.element;
        }

        //method
        public getRefNextNode(): ListNode<E> {

            this.supposeHasNextNode();

            return this.nextNode;
        }

        //method
        public hasNextNode(): boolean {
            return (this.nextNode != null);
        }

        //method
        public removeNextNode() {
            this.nextNode = null;
        }

        //method
        public setNextNode(nextNode: ListNode<E>) {

            if (nextNode == null) {
                throw new Error("The given next node is null.");
            }

            this.nextNode = nextNode;
        }

        //method
        private supposeHasNextNode() {
            if (!this.hasNextNode) {
                throw new Error("The current list node has no next node.");
            }
        }
    }
}
