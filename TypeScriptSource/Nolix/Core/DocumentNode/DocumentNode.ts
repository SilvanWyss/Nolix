//import
import {List} from '../Container/List';

//class
export class DocumentNode {

    //optionl attribute
    private header: string;

    //multi-attribute
    private attributes: List<DocumentNode> = new List<DocumentNode>();

    //method
    public addAttribute(attribute: DocumentNode): DocumentNode {

        this.attributes.addAtEnd(attribute.getCopy())

        return this;
    }

    //method
    public getAttributeCount(): number {

        //For a better performance, this implementation does not use all comfortable methods.
        return this.attributes.getSize();
    }

    //method
    public getCopy(): DocumentNode {

        let copy = new DocumentNode();

        if (this.hasHeader()) {
            copy.setHeader(this.getHeader());
        }

        //For a better performance, this implementation does not use all comfortable methods.
        this.attributes.forEach(a => copy.addAttribute(a));

        return copy;
    }

    //method
    public getHeader(): string {

        this.supposeHasHeader();

        return this.header;
    }

    //method
    public getRefAttributes(): List<DocumentNode> {
        return this.attributes;
    }

    //method
    public hasHeader(): boolean {
        return (this.header != null);
    }

    //method
    public removeHeader(): DocumentNode {

        this.header = null;

        return this;
    }

    //method
    public setHeader(header: string): DocumentNode {

        if (header == null) {
            throw new Error("The given header is null.");
        }

        if (header.length == 0) {
            throw new Error("The given header is empty.");
        }

        this.header = header;

        return this;
    }

    //method
    private supposeHasHeader(): void {
        if (!this.hasHeader()) {
            throw new Error("The current document node does not have a header.");
        }
    }
}
