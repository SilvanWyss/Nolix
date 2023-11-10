define("Core/CommonType/CommonTypeConstant/StringCatalogue", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class StringCatalogue {
        constructor() { }
    }
    StringCatalogue.BINARY_PREFIX = '0b';
    StringCatalogue.EMPTY = '';
    StringCatalogue.HEXADECIMAL_PREFIX = '0x';
    exports.StringCatalogue = StringCatalogue;
});
define("Core/CommonType/CommonTypeHelper/GlobalStringHelper", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class GlobalStringHelper {
        static createStringOfSpaces(spaceCount) {
            if (spaceCount < 0) {
                throw new Error('The given space count is negative.');
            }
            var string = '';
            for (var i = 1; i <= spaceCount; i++) {
                string += ' ';
            }
            return string;
        }
        static createStringWithReplacedParts(originalString, searchedString, replacingString) {
            var string = originalString;
            while (string.includes(searchedString)) {
                string = string.replace(searchedString, replacingString);
            }
            return string;
        }
    }
    exports.GlobalStringHelper = GlobalStringHelper;
});
define("Core/Container/Base/Container", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Container {
        contains(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return true;
                }
            }
            return false;
        }
        containsAny() {
            for (const e of this) {
                return true;
            }
            return false;
        }
        containsGivenElement(element) {
            for (const e of this) {
                if (Object.is(e, element)) {
                    return true;
                }
            }
            return false;
        }
        containsOne() {
            return (this.getSize() === 1);
        }
        forEach(elementTaker) {
            for (const e of this) {
                elementTaker(e);
            }
        }
        getOneAsString() {
            return this.getRefOne().toString();
        }
        getRefOne() {
            var element = undefined;
            for (const e of this) {
                if (element !== undefined) {
                    throw new Error('The current List contains none or several elements.');
                }
                element = e;
            }
            if (element === undefined) {
                throw new Error('The current List contains none or several elements.');
            }
            return element;
        }
        isEmpty() {
            return (this.getSize() === 0);
        }
        toString() {
            return this.toStringWithSeparator(',');
        }
        toStringInBrackets() {
            return ('(' + this.toString() + ')');
        }
        toStringInBracketsWithSeparator(separator) {
            return ('(' + this.toStringWithSeparator(separator) + ')');
        }
        toStringWithSeparator(separator) {
            if (separator === null) {
                throw new Error('The given separator is null.');
            }
            if (separator === undefined) {
                throw new Error('The given separator is undefined.');
            }
            var string = '';
            var begin = true;
            for (const e of this) {
                if (begin) {
                    string += e.toString();
                    begin = false;
                }
                else {
                    string += separator + e.toString();
                }
            }
            return string;
        }
    }
    exports.Container = Container;
});
define("Core/Container/LinkedList/LinkedListNode", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class LinkedListNode {
        constructor(element) {
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.element = element;
        }
        contains(element) {
            return (Object.is(this.element, element));
        }
        getRefElement() {
            return this.element;
        }
        getRefNextNode() {
            if (this.nextNode === undefined) {
                throw new Error('The current list node does not have a next node.');
            }
            return this.nextNode;
        }
        hasNextNode() {
            return (this.nextNode !== undefined);
        }
        hasNextNodeWith(element) {
            return (this.nextNode !== undefined && this.nextNode.element === element);
        }
        removeNextNode() {
            this.nextNode = undefined;
        }
        setNextNode(nextNode) {
            if (nextNode === null) {
                throw new Error('The given next node is null.');
            }
            if (nextNode === undefined) {
                throw new Error('The given next node is undefined.');
            }
            this.nextNode = nextNode;
        }
    }
    exports.LinkedListNode = LinkedListNode;
});
define("Core/Container/LinkedList/LinkedListIterator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class LinkedListIterator {
        constructor(currentNode) {
            this.currentNode = undefined;
            this.lastElement = undefined;
            this.currentNode = currentNode;
        }
        next() {
            if (this.currentNode === undefined) {
                return { done: true, value: undefined };
            }
            this.lastElement = this.currentNode.getRefElement();
            if (!this.currentNode.hasNextNode()) {
                this.currentNode = undefined;
                return { done: false, value: this.lastElement };
            }
            this.currentNode = this.currentNode.getRefNextNode();
            return { done: false, value: this.lastElement };
        }
        return() {
            return { done: true, value: this.lastElement };
        }
    }
    exports.LinkedListIterator = LinkedListIterator;
});
define("Core/Container/LinkedList/LinkedList", ["require", "exports", "Core/Container/Base/Container", "Core/Container/LinkedList/LinkedListIterator", "Core/Container/LinkedList/LinkedListNode"], function (require, exports, Container_1, LinkedListIterator_1, LinkedListNode_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class LinkedList extends Container_1.Container {
        constructor() {
            super(...arguments);
            this.elementCount = 0;
            this.beginNode = undefined;
            this.endNode = undefined;
        }
        static withElement(element) {
            const list = new LinkedList();
            list.addAtEnd(element);
            return list;
        }
        addAtBegin(element) {
            const newNode = new LinkedListNode_1.LinkedListNode(element);
            if (this.elementCount === 0) {
                this.endNode = newNode;
            }
            else {
                newNode.setNextNode(this.beginNode);
            }
            this.beginNode = newNode;
            this.elementCount++;
        }
        addAtEnd(element) {
            const newNode = new LinkedListNode_1.LinkedListNode(element);
            if (this.elementCount === 0) {
                this.beginNode = newNode;
            }
            else {
                this.endNode.setNextNode(newNode);
            }
            this.endNode = newNode;
            this.elementCount++;
        }
        addElementsAtEnd(elements) {
            for (const e of elements) {
                this.addAtEnd(e);
            }
        }
        clear() {
            if (this.elementCount > 0) {
                this.beginNode = undefined;
                this.endNode = undefined;
                this.elementCount = 0;
            }
        }
        getRefAt(index) {
            if (index < 1) {
                throw new Error('The given index is not positive.');
            }
            var i = 1;
            for (const e of this) {
                if (i === index) {
                    return e;
                }
                i++;
            }
            throw new Error('The given index is bigger than the size of the current List.');
        }
        getRefFirst() {
            if (this.elementCount === 0) {
                throw new Error("The current List is empty.");
            }
            return this.beginNode.getRefElement();
        }
        getRefFirstByCondition(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return e;
                }
            }
            throw new Error("The current List does not contain an element the given selector selects.");
        }
        getRefFirstByConditionOrNull(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return e;
                }
            }
            return null;
        }
        getRefFirstOrNull() {
            if (this.elementCount === 0) {
                return null;
            }
            return this.beginNode.getRefElement();
        }
        getRefLast() {
            if (this.elementCount === 0) {
                throw new Error("The current List is empty.");
            }
            return this.endNode.getRefElement();
        }
        getRefSelected(selector) {
            const selectedElements = new LinkedList();
            for (const e of this) {
                if (selector(e)) {
                    selectedElements.addAtEnd(e);
                }
            }
            return selectedElements;
        }
        getSize() {
            return this.elementCount;
        }
        refill(element) {
            this.clear();
            this.addElementsAtEnd(element);
        }
        removeFirst() {
            if (this.elementCount === 0) {
                throw new Error('The current List is empty.');
            }
            if (this.containsOne()) {
                this.clear();
            }
            else {
                this.beginNode = this.beginNode.getRefNextNode();
                this.elementCount--;
            }
        }
        removeFirst2(element) {
            if (this.elementCount === 0) {
                return;
            }
            if (element === this.getRefFirst()) {
                this.removeFirst();
                return;
            }
            var iteratorNode = this.beginNode;
            while (iteratorNode.hasNextNode()) {
                if (iteratorNode.hasNextNodeWith(element)) {
                    this.removeNextNode(iteratorNode);
                    return;
                }
                iteratorNode = iteratorNode.getRefNextNode();
            }
            throw new Error('The current List does not contain the given element.');
        }
        to(extractor) {
            const list = new LinkedList();
            for (const e of this) {
                list.addAtEnd(extractor(e));
            }
            return list;
        }
        toStrings() {
            const strings = new LinkedList();
            for (const e of this) {
                strings.addAtEnd(e.toString());
            }
            return strings;
        }
        [Symbol.iterator]() {
            return new LinkedListIterator_1.LinkedListIterator(this.beginNode);
        }
        removeNextNode(node) {
            const nextNode = node.getRefNextNode();
            if (!nextNode.hasNextNode()) {
                nextNode.removeNextNode();
                this.endNode = node;
            }
            else {
                node.setNextNode(nextNode.getRefNextNode());
            }
            this.elementCount--;
            nextNode.removeNextNode();
        }
    }
    exports.LinkedList = LinkedList;
});
define("Core/Container/Matrix/MatrixIterator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class MatrixIterator {
        constructor(parentMatrix) {
            if (parentMatrix === undefined) {
                console.log('The given parentMatrix is undefined.');
            }
            if (parentMatrix === null) {
                console.log('The given parentMatrix is null.');
            }
            this.parentMatrix = parentMatrix;
            this.currentElementIndex = 1;
        }
        next() {
            if (this.currentElementIndex > this.parentMatrix.getSize()) {
                return { done: true, value: undefined };
            }
            this.currentElementIndex++;
            return {
                done: this.currentElementIndex > this.parentMatrix.getSize(),
                value: this.parentMatrix.getRefAt(this.currentElementIndex - 1)
            };
        }
    }
    exports.MatrixIterator = MatrixIterator;
});
define("Core/Container/Matrix/Matrix", ["require", "exports", "Core/Container/Base/Container", "Core/Container/Matrix/MatrixIterator"], function (require, exports, Container_2, MatrixIterator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Matrix extends Container_2.Container {
        constructor() {
            super();
            this.elements = new Array();
        }
        addColumn(elements) {
            if (this.isEmpty()) {
                this.addColumnWhenIsEmpty(elements);
            }
            else {
                this.addColumnWhenIsNotEmpty(elements);
            }
        }
        addRow(elements) {
            if (this.isEmpty()) {
                this.addRowWhenIsEmpty(elements);
            }
            else {
                this.addRowWhenIsNotEmpty(elements);
            }
        }
        getColumnCount() {
            const rowCount = this.getRowCount();
            if (rowCount === 0) {
                return 0;
            }
            return this.elements[0].length;
        }
        getColumnIndexOf(index) {
            this.assertContainsAt(index);
            return ((index - 1) % this.getColumnCount() + 1);
        }
        getCopy() {
            const matrix = new Matrix();
            const rowCount = this.getRowCount();
            for (var i = 0; i < rowCount; i++) {
                matrix.addRow(this.elements[i]);
            }
            return matrix;
        }
        getRefAt(index) {
            return this.getRefAtRowAndColumn(this.getRowIndexOf(index), this.getColumnIndexOf(index));
        }
        getRefAtRowAndColumn(rowIndex, columnIndex) {
            this.assertContainsAtRowAndColumn(rowIndex, columnIndex);
            return this.elements[rowIndex - 1][columnIndex - 1];
        }
        getRowCount() {
            return this.elements.length;
        }
        getRowIndexOf(index) {
            this.assertContainsAt(index);
            return (Math.floor((index - 1) / this.getColumnCount()) + 1);
        }
        getSize() {
            if (this.elements.length === 0) {
                return 0;
            }
            return (this.elements.length * this.elements[0].length);
        }
        setAtIndex(index, element) {
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.elements[this.getRowIndexOf(index) - 1][this.getColumnIndexOf(index) - 1] = element;
        }
        setAtRowAndColumn(rowIndex, columnIndex, element) {
            this.assertContainsAtRowAndColumn(rowIndex, columnIndex);
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.elements[rowIndex - 1][columnIndex - 1] = element;
        }
        [Symbol.iterator]() {
            return new MatrixIterator_1.MatrixIterator(this);
        }
        addColumnWhenIsEmpty(elements) {
            if (elements.length == 0) {
                throw new Error('There are not given any elements.');
            }
            const column = new Array(elements.length);
            for (const e of elements) {
                const array = new Array(1);
                array[0] = e;
                this.elements.push(array);
            }
        }
        addColumnWhenIsNotEmpty(elements) {
            if (elements.length != this.getRowCount()) {
                throw new Error('There are given ' + elements.length + ' elements.');
            }
            const rowCount = this.getRowCount();
            for (var i = 0; i < rowCount; i++) {
                this.elements[i].push(elements[i]);
            }
        }
        addRowWhenIsEmpty(elements) {
            if (elements.length == 0) {
                throw new Error('There are not given any elements.');
            }
            this.elements.push(Array.from(elements));
        }
        addRowWhenIsNotEmpty(elements) {
            if (elements.length != this.getColumnCount()) {
                throw new Error('There are given ' + elements.length + ' elements.');
            }
            this.elements.push(Array.from(elements));
        }
        assertContainsAt(index) {
            if (index < 1) {
                throw new Error('The given index ' + index + ' is not positive.');
            }
            if (index > this.getSize()) {
                throw new Error('The given index ' + index + ' is bigger than the number of elements of the current Matrix.');
            }
        }
        assertContainsAtRowAndColumn(rowIndex, columnIndex) {
            if (rowIndex < 1) {
                throw new Error('The given rowIndex ' + rowIndex + ' is not positive.');
            }
            if (rowIndex > this.getRowCount()) {
                throw new Error('The given rowIndex '
                    + rowIndex
                    + ' is bigger than the number of rows of the current Matrix, which is '
                    + this.getRowCount()
                    + ' .');
            }
            if (columnIndex < 1) {
                throw new Error('The given columnIndex ' + columnIndex + ' is not positive.');
            }
            if (columnIndex > this.getColumnCount()) {
                throw new Error('The given columnIndex '
                    + columnIndex
                    + ' is bigger than the number of columns of the current Matrix, which is'
                    + this.getColumnCount()
                    + ' .');
            }
        }
    }
    exports.Matrix = Matrix;
});
define("Core/Container/Pair/Pair", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Pair {
        constructor(element1, element2) {
            if (element1 === null) {
                throw new Error('The given element1 is null.');
            }
            if (element1 === undefined) {
                throw new Error('The given element1 is undefined.');
            }
            if (element2 === null) {
                throw new Error('The given element2 is null.');
            }
            if (element2 === undefined) {
                throw new Error('The given element2 is undefined.');
            }
            this.element1 = element1;
            this.element2 = element2;
        }
        getRefElement1() {
            return this.element1;
        }
        getRefElement2() {
            return this.element2;
        }
    }
    exports.Pair = Pair;
});
define("Core/Container/SingleContainer/SingleContainer", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class SingleContainer {
        constructor(optionalElement) {
            this.element = optionalElement;
        }
        static withElement(element) {
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            return new SingleContainer(element);
        }
        static withoutElement() {
            return new SingleContainer(undefined);
        }
        contains(element) {
            return (Object.is(this.element, element));
        }
        containsAny() {
            return (this.element !== undefined);
        }
        getRefElement() {
            if (this.element === undefined) {
                throw new Error('The current SingleContainer does not contain an element.');
            }
            return this.element;
        }
        isEmpty() {
            return (this.element === undefined);
        }
    }
    SingleContainer.EMPTY_CONTAINER = new SingleContainer(undefined);
    exports.SingleContainer = SingleContainer;
});
define("Core/Document/Node/Node", ["require", "exports", "Core/Container/LinkedList/LinkedList"], function (require, exports, LinkedList_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Node {
        constructor() {
            this.attributes = new LinkedList_1.LinkedList();
        }
        static createOriginStringFromReproducingString(reproducinString) {
            var originString = '';
            for (var i = 0; i < reproducinString.length; i++) {
                switch (reproducinString[i]) {
                    case '$':
                        switch (reproducinString[i + 1]) {
                            case 'D':
                                originString += '.';
                                break;
                            case 'M':
                                originString += ',';
                                break;
                            case 'X':
                                originString += '$';
                                break;
                            case 'O':
                                originString += '(';
                                break;
                            case 'C':
                                originString += ')';
                                break;
                            default:
                                throw new Error('The given reproducing string is not valid.');
                        }
                        i++;
                        break;
                    default:
                        originString += reproducinString[i];
                        break;
                }
            }
            return originString;
        }
        static createReproducingString(string) {
            if (string === null) {
                throw new Error('The given string is null.');
            }
            if (string === undefined) {
                throw new Error('The given string is undefined.');
            }
            return string
                .replace('$', this.DOLLAR_SYMBOL_CODE)
                .replace('.', this.DOT_CODE)
                .replace(',', this.COMMA_CODE)
                .replace('(', this.OPEN_BRACKET_CODE)
                .replace(')', this.CLOSED_BRACKET_CODE);
        }
        static fromNumberPair(numberPair) {
            return new Node()
                .addAttribute(Node.withHeaderFromNumber(numberPair.getRefElement1()))
                .addAttribute(Node.withHeaderFromNumber(numberPair.getRefElement2()));
        }
        static fromString(string) {
            return new Node().resetFrom(string);
        }
        static withAttribute(attribute) {
            return new Node().addAttribute(attribute);
        }
        static withHeader(header) {
            return new Node().setHeader(header);
        }
        static withHeaderAndAttribute(header, attribute) {
            return new Node().setHeader(header).addAttribute(attribute);
        }
        static withHeaderAndAttributes(header, attributes) {
            return new Node().setHeader(header).addAttributes(attributes);
        }
        static withHeaderFromNumber(header) {
            return new Node().setHeader(header.toString());
        }
        addAttribute(attribute) {
            this.attributes.addAtEnd(attribute.getCopy());
            return this;
        }
        addAttributeFromNumber(attribute) {
            this.addAttribute(Node.withHeaderFromNumber(attribute));
            return this;
        }
        addAttributes(attributes) {
            attributes.forEach(a => this.addAttribute(a));
            return this;
        }
        containsAttributes() {
            return this.attributes.containsAny();
        }
        getAttributeCount() {
            return this.attributes.getSize();
        }
        getCopy() {
            var copy = new Node();
            if (this.header !== undefined) {
                copy.setHeader(this.getHeader());
            }
            this.attributes.forEach(a => copy.addAttribute(a));
            return copy;
        }
        getHeader() {
            if (this.header === undefined) {
                throw new Error("The current document node does not have a header.");
            }
            return this.header;
        }
        getOneAttributeAsNumber() {
            return this.getRefOneAttribute().toNumber();
        }
        getOneAttributeHeader() {
            return this.getRefOneAttribute().getHeader();
        }
        getRefAttributeAtIndex(index) {
            return this.attributes.getRefAt(index);
        }
        getRefAttributes() {
            return this.attributes;
        }
        getRefFirstAttributeWithHeader(header) {
            return this.attributes.getRefFirstByCondition(a => a.hasGivenHeader(header));
        }
        getRefOneAttribute() {
            return this.attributes.getRefOne();
        }
        getReproducingHeader() {
            return Node.createReproducingString(this.getHeader());
        }
        hasHeader() {
            return (this.header !== undefined);
        }
        hasGivenHeader(header) {
            return (this.header !== undefined && this.header === header);
        }
        removeAttributes() {
            this.attributes.clear();
            return this;
        }
        removeHeader() {
            this.header = undefined;
            return this;
        }
        reset() {
            this.removeHeader();
            this.removeAttributes();
            return this;
        }
        resetFrom(string) {
            this.reset();
            if (this.setAndGetEndIndex(string, 0) !== string.length - 1) {
                throw new Error('The given string does not represent a document node.');
            }
            return this;
        }
        setHeader(header) {
            if (header === null) {
                throw new Error("The given header is null.");
            }
            if (header === undefined) {
                throw new Error('The given header is undefined.');
            }
            if (header.length === 0) {
                throw new Error("The given header is empty.");
            }
            this.header = header;
            return this;
        }
        toNumber() {
            if (this.attributes.containsAny()) {
                throw new Error('The current Node does not represent a number: ' + this.toString());
            }
            return Number(this.header);
        }
        toString() {
            var string = '';
            if (this.header !== undefined) {
                string += this.getReproducingHeader();
            }
            if (this.attributes.containsAny()) {
                string += '(' + this.getRefAttributes().toString() + ')';
            }
            return string;
        }
        setAndGetEndIndex(string, startIndex) {
            var index = startIndex;
            var endIndex = -1;
            while (index < string.length) {
                const character = string[index];
                if (character === '(') {
                    break;
                }
                else if (character === ',' || character === ')') {
                    endIndex = index - 1;
                    break;
                }
                index++;
            }
            if (index > startIndex) {
                this.setHeader(string.substring(startIndex, index));
            }
            if (index === string.length) {
                return (index - 1);
            }
            if (endIndex !== -1) {
                return endIndex;
            }
            if (index < string.length) {
                const documentNode = new Node();
                index = documentNode.setAndGetEndIndex(string, index + 1) + 1;
                this.addAttribute(documentNode);
            }
            while (index < string.length) {
                switch (string[index]) {
                    case ',':
                        const documentNode = new Node();
                        index = documentNode.setAndGetEndIndex(string, index + 1) + 1;
                        this.addAttribute(documentNode);
                        break;
                    case ')':
                        return index;
                    default:
                        throw new Error('The given string does not represent a Node: ' + string);
                }
            }
        }
    }
    Node.DOT_CODE = '$D';
    Node.COMMA_CODE = '$M';
    Node.DOLLAR_SYMBOL_CODE = '$X';
    Node.OPEN_BRACKET_CODE = '$O';
    Node.CLOSED_BRACKET_CODE = '$C';
    exports.Node = Node;
});
define("Core/Document/ChainedNode/Task", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var Task;
    (function (Task) {
        Task[Task["DO_NOTHING"] = 0] = "DO_NOTHING";
        Task[Task["READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE"] = 1] = "READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE";
        Task[Task["READ_NEXT_NODE"] = 2] = "READ_NEXT_NODE";
    })(Task = exports.Task || (exports.Task = {}));
});
define("Core/Document/ChainedNode/ChainedNode", ["require", "exports", "Core/Container/LinkedList/LinkedList", "Core/Document/Node/Node", "Core/CommonType/CommonTypeConstant/StringCatalogue", "Core/Document/ChainedNode/Task"], function (require, exports, LinkedList_2, Node_1, StringCatalogue_1, Task_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ChainedNode {
        constructor() {
            this.header = undefined;
            this.nextNode = undefined;
            this.attributes = new LinkedList_2.LinkedList();
        }
        static createReproducingString(string) {
            var reprodudingString = '';
            for (var i = 0; i < string.length; i++) {
                switch (string[i]) {
                    case '$':
                        reprodudingString += this.DOLLAR_SYMBOL_CODE;
                        break;
                        ;
                    case '.':
                        reprodudingString += this.DOT_CODE;
                        break;
                    case ',':
                        reprodudingString += this.COMMA_CODE;
                        break;
                    case '(':
                        reprodudingString += this.OPEN_BRACKET_CODE;
                        break;
                    case ')':
                        reprodudingString += this.CLOSED_BRACKET_CODE;
                        break;
                    default:
                        reprodudingString += string[i];
                        break;
                }
            }
            return reprodudingString;
        }
        static fromNode(node) {
            const chainedNode = new ChainedNode();
            if (node.hasHeader()) {
                chainedNode.setHeader(node.getHeader());
            }
            for (const a of node.getRefAttributes()) {
                chainedNode.addAttributeFromNode(a);
            }
            return chainedNode;
        }
        static fromString(string) {
            const chainedNode = new ChainedNode();
            chainedNode.resetFromString(string);
            return chainedNode;
        }
        static withHeader(header) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            return chainedNode;
        }
        static withHeaderAndAttribute(header, attribute) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.attributes.addAtEnd(attribute);
            return chainedNode;
        }
        static withHeaderAndAttributeAndNextNode(header, attribute, nextNode) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.attributes.addAtEnd(attribute);
            chainedNode.setNextNode(nextNode);
            return chainedNode;
        }
        static withHeaderAndAttributeFromNode(header, attribute) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.addAttributeFromNode(attribute);
            return chainedNode;
        }
        static withHeaderAndAttributes(header, attributes) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.setAttributes(attributes);
            return chainedNode;
        }
        static withHeaderAndAttributesFromNodes(header, attributes) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.setAttributesFromNodes(attributes);
            return chainedNode;
        }
        static withHeaderAndNextNode(header, nextNode) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.setNextNode(nextNode);
            return chainedNode;
        }
        static withHeaderFromNumber(header) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header.toString());
            return chainedNode;
        }
        containsAttributes() {
            return this.attributes.containsAny();
        }
        getAttributeCount() {
            return this.attributes.getSize();
        }
        getAttributeAt(index) {
            return this.attributes.getRefAt(index);
        }
        getAttributeAtAsNode(index) {
            return this.getAttributeAt(index).toNode();
        }
        getAttributes() {
            return this.attributes;
        }
        getAttributesAsNodes() {
            return this.attributes.to(a => a.toNode());
        }
        getAttributesAsString() {
            return this.attributes.toString();
        }
        getAttributesAsStrings() {
            return this.attributes.toStrings();
        }
        getFirstAttribute() {
            return this.attributes.getRefFirst();
        }
        getFirstAttribtueAsNode() {
            return this.getFirstAttribute().toNode();
        }
        getFirstAttributeAsString() {
            return this.getFirstAttribute().toString();
        }
        getHeader() {
            if (this.header === undefined) {
                throw new Error('The current ChainedNode does not have a header.');
            }
            return this.header;
        }
        getHeaderOrEmptyString() {
            if (this.header === undefined) {
                return StringCatalogue_1.StringCatalogue.EMPTY;
            }
            return this.header;
        }
        getNextNode() {
            if (this.nextNode === null) {
                throw new Error('The current ChainedNode does not have a next node.');
            }
            return this.nextNode;
        }
        getNextNodeAsString() {
            return this.getNextNode().toString();
        }
        getOneAttribute() {
            return this.attributes.getRefOne();
        }
        getOneAttributeAsNumber() {
            return this.getOneAttribute().toNumber();
        }
        getOneAttributeAsNode() {
            return this.getOneAttribute().toNode();
        }
        getOneAttributeAsString() {
            return this.getOneAttribute().toString();
        }
        getReproducingHeader() {
            return ChainedNode.createReproducingString(this.getHeader());
        }
        hasGivenHeader(header) {
            return (this.hasHeader() && this.getHeader() === header);
        }
        hasHeader() {
            return (this.header !== undefined);
        }
        hasNextNode() {
            return (this.nextNode !== undefined);
        }
        toNumber() {
            if (!this.hasHeader() || this.containsAttributes() || this.hasNextNode()) {
                throw new Error('The current ChainedNode does not represent a number.');
            }
            return Number(this.getHeader());
        }
        toNode() {
            if (this.hasNextNode()) {
                throw new Error('The current ChainedNode does not represent a Node.');
            }
            const node = new Node_1.Node();
            if (this.hasHeader()) {
                node.setHeader(this.getHeader());
            }
            for (const a of this.getAttributes()) {
                node.addAttribute(a.toNode());
            }
            return node;
        }
        toString() {
            let string = '';
            if (this.hasHeader()) {
                string += ChainedNode.createReproducingString(this.getHeader());
            }
            if (this.containsAttributes()) {
                string += '(';
                let atBegin = true;
                for (const a of this.getAttributes()) {
                    if (atBegin) {
                        atBegin = false;
                        string += a.toString();
                    }
                    else {
                        string += ',' + a.toString();
                    }
                }
                string += ')';
            }
            if (this.hasNextNode()) {
                string += '.' + this.getNextNode().toString();
            }
            return string;
        }
        toStringInBrackets() {
            return ('(' + this.toString() + ')');
        }
        addAttributeFromNode(attribute) {
            this.attributes.addAtEnd(ChainedNode.fromNode(attribute));
        }
        reset() {
            this.header = undefined;
            this.attributes.clear();
            this.nextNode = undefined;
        }
        resetFromString(string) {
            this.reset();
            if (this.setAndGetNextIndex(string, 0) !== string.length) {
                throw new Error('The given string does not represent a ChainedNode: ' + string);
            }
        }
        setAndGetNextIndex(substring, startIndex) {
            var nextIndex = startIndex;
            var taskAfterSetProbableHeader = Task_1.Task.DO_NOTHING;
            var headerLength = 0;
            while (nextIndex < substring.length) {
                var character = substring.charAt(nextIndex);
                if (character == '(') {
                    taskAfterSetProbableHeader = Task_1.Task.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE;
                    nextIndex++;
                    break;
                }
                if (character == ',') {
                    break;
                }
                if (character == ')') {
                    break;
                }
                if (character == '.') {
                    taskAfterSetProbableHeader = Task_1.Task.READ_NEXT_NODE;
                    nextIndex++;
                    break;
                }
                nextIndex++;
                headerLength++;
            }
            if (headerLength > 0) {
                this.header = Node_1.Node.createOriginStringFromReproducingString(substring.substring(startIndex, startIndex + headerLength));
            }
            var readNextNode = false;
            switch (taskAfterSetProbableHeader) {
                case Task_1.Task.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE:
                    const node = new ChainedNode();
                    nextIndex = node.setAndGetNextIndex(substring, nextIndex);
                    this.attributes.addAtEnd(node);
                    while (nextIndex < substring.length) {
                        const character = substring.charAt(nextIndex);
                        if (character == ',') {
                            const node2 = new ChainedNode();
                            nextIndex = node2.setAndGetNextIndex(substring, nextIndex + 1);
                            this.attributes.addAtEnd(node2);
                        }
                        else if (character == ')') {
                            nextIndex++;
                            break;
                        }
                    }
                    if (nextIndex < substring.length - 1 && substring.charAt(nextIndex) == '.') {
                        nextIndex++;
                        readNextNode = true;
                    }
                    break;
                case Task_1.Task.DO_NOTHING:
                    return nextIndex;
                case Task_1.Task.READ_NEXT_NODE:
                    readNextNode = true;
            }
            if (!readNextNode) {
                return nextIndex;
            }
            this.nextNode = new ChainedNode();
            return this.nextNode.setAndGetNextIndex(substring, nextIndex);
        }
        setAttributes(attributes) {
            this.attributes.clear();
            this.attributes.addElementsAtEnd(attributes);
        }
        setAttributesFromNodes(attributes) {
            this.attributes.clear();
            for (const a of attributes) {
                this.addAttributeFromNode(a);
            }
        }
        setHeader(header) {
            if (header === null) {
                throw new Error('The given header is null.');
            }
            if (header === undefined) {
                throw new Error('The given header is undefined.');
            }
            if (header.length == 0) {
                throw new Error('The given header is empty.');
            }
            this.header = header;
        }
        setNextNode(nextNode) {
            if (nextNode === null) {
                throw new Error('The given nextNode is null.');
            }
            if (nextNode === undefined) {
                throw new Error('The given nextNode is undefined.');
            }
            this.nextNode = nextNode;
        }
    }
    ChainedNode.DOT_CODE = '$D';
    ChainedNode.COMMA_CODE = '$M';
    ChainedNode.DOLLAR_SYMBOL_CODE = '$X';
    ChainedNode.OPEN_BRACKET_CODE = '$O';
    ChainedNode.CLOSED_BRACKET_CODE = '$C';
    exports.ChainedNode = ChainedNode;
});
define("Core/Math/GlobalCalculator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class GlobalCalculator {
        static getMax(...values) {
            if (values === undefined) {
                throw new Error('The given values is undefined.');
            }
            if (values === null) {
                throw new Error('The given values is empty.');
            }
            if (values.length === 0) {
                throw new Error('There is not given a value.');
            }
            var max = values[0];
            for (const v of values) {
                if (v > max) {
                    max = v;
                }
            }
            return max;
        }
        static getMin(...values) {
            if (values === undefined) {
                throw new Error('The given values is undefined.');
            }
            if (values === null) {
                throw new Error('The given values is empty.');
            }
            if (values.length === 0) {
                throw new Error('There is not given a value.');
            }
            var min = values[0];
            for (const v of values) {
                if (v < min) {
                    min = v;
                }
            }
            return min;
        }
        constructor() { }
    }
    exports.GlobalCalculator = GlobalCalculator;
});
define("Core/Net/EndPoint/Protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Protocol {
    }
    Protocol.TARGET_PREFIX = 'T';
    Protocol.MAIN_TARGET_PREFIX = 'A';
    Protocol.MESSAGE_PREFIX = 'M';
    exports.Protocol = Protocol;
});
define("CoreAPI/NetAPI/WebSocketAPI/WebSocketType", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var WebSocketType;
    (function (WebSocketType) {
        WebSocketType[WebSocketType["BASE_WEB_SOCKET"] = 0] = "BASE_WEB_SOCKET";
        WebSocketType[WebSocketType["SECURE_WEB_SOCKET"] = 1] = "SECURE_WEB_SOCKET";
    })(WebSocketType = exports.WebSocketType || (exports.WebSocketType = {}));
});
define("Core/Net/EndPoint/NetEndPoint", ["require", "exports", "Core/Net/EndPoint/Protocol", "CoreAPI/NetAPI/WebSocketAPI/WebSocketType"], function (require, exports, Protocol_1, WebSocketType_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint {
        constructor(ip, port, optionalTarget, webSocketType) {
            this.onMessage = (messageEvent) => {
                this.receive(messageEvent.data);
            };
            this.onOpen = (event) => {
                console.log("The current NetEndPoint2 has been connected.");
                const targetMessage = (this.target === undefined) ?
                    Protocol_1.Protocol.MAIN_TARGET_PREFIX :
                    Protocol_1.Protocol.TARGET_PREFIX + this.target;
                this.sendRawMessage(targetMessage);
            };
            if (ip === null) {
                throw new Error('The given ip is null.');
            }
            if (ip === undefined) {
                throw new Error('The given ip is undefined.');
            }
            if (ip.length === 0) {
                throw new Error('The given ip is empty.');
            }
            if (port < 0 || port > 65535) {
                throw new Error('The given port is not in [0, 65535].');
            }
            if (webSocketType === null) {
                throw new Error('The given webSocketType is null.');
            }
            if (webSocketType === undefined) {
                throw new Error('The given webSocketType is undefined.');
            }
            if (optionalTarget.containsAny()) {
                if (optionalTarget.getRefElement().length === 0) {
                    throw new Error('The given target is empty.');
                }
                this.target = optionalTarget.getRefElement();
            }
            else {
                this.target = undefined;
            }
            switch (webSocketType) {
                case WebSocketType_1.WebSocketType.BASE_WEB_SOCKET:
                    this.webSocket = new WebSocket('ws://' + ip + ':' + port);
                    break;
                case WebSocketType_1.WebSocketType.SECURE_WEB_SOCKET:
                    this.webSocket = new WebSocket('wss://' + ip + ':' + port + '/websocket');
                    break;
            }
            this.webSocket.onopen = this.onOpen;
            this.webSocket.onmessage = this.onMessage;
        }
        getTarget() {
            if (this.target === undefined) {
                throw new Error('The current NetEndPoint2 does not have a target.');
            }
            return this.target;
        }
        hasReceiver() {
            return (this.receiver !== undefined);
        }
        hasTarget() {
            return (this.target !== undefined);
        }
        send(message) {
            this.sendRawMessage(Protocol_1.Protocol.MESSAGE_PREFIX + message);
        }
        setReceiver(receiver) {
            if (receiver === null) {
                throw new Error('The given receiver is null.');
            }
            if (receiver === undefined) {
                throw new Error('The given receiver is undefined.');
            }
            this.receiver = receiver;
        }
        receive(message) {
            if (message === null) {
                throw new Error('The given message is null.');
            }
            if (message === undefined) {
                throw new Error('The given message is undefined.');
            }
            if (message.length === 0) {
                throw new Error('The given message is empty.');
            }
            if (message.endsWith('\r\n')) {
                message = message.substring(0, message.length - 2);
            }
            switch (message[0]) {
                case Protocol_1.Protocol.MESSAGE_PREFIX:
                    if (this.receiver === undefined) {
                        throw new Error('The current NetEndPoint2 does not have a receiver.');
                    }
                    this.receiver(message.substring(1, message.length));
                    break;
                default:
                    throw new Error('The given message is not valid: ' + message);
            }
        }
        sendRawMessage(rawMessage) {
            this.webSocket.send(rawMessage);
        }
    }
    exports.NetEndPoint = NetEndPoint;
});
define("Core/Net/EndPoint2/MessageRole", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var MessageRole;
    (function (MessageRole) {
        MessageRole[MessageRole["TARGET_MESSAGE"] = 0] = "TARGET_MESSAGE";
        MessageRole[MessageRole["STANDARD_MESSAGE"] = 1] = "STANDARD_MESSAGE";
        MessageRole[MessageRole["REPLY_MESSAGE"] = 2] = "REPLY_MESSAGE";
        MessageRole[MessageRole["ERROR_MESSAGE"] = 3] = "ERROR_MESSAGE";
    })(MessageRole = exports.MessageRole || (exports.MessageRole = {}));
});
define("Core/Net/EndPoint2/Package", ["require", "exports", "Core/Net/EndPoint2/MessageRole"], function (require, exports, MessageRole_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Package {
        constructor(index, messageRole, message) {
            if (index === null) {
                throw new Error('The given index is null.');
            }
            if (index === undefined) {
                throw new Error('The given index is undefined.');
            }
            if (index < 1) {
                throw new Error('The given index is not positive.');
            }
            if (messageRole === null) {
                throw new Error('The given message role is null.');
            }
            if (messageRole === undefined) {
                throw new Error('The given message role is undefined.');
            }
            if (message === null) {
                throw new Error('The given message is null.');
            }
            if (message === undefined) {
                throw new Error('The given message is undefined.');
            }
            this.index = index;
            this.message = message;
            this.messageRole = messageRole;
        }
        static createFromString(string) {
            return new Package(Number.parseInt(string.substring(0, 8)), Package.createMessageRole(string.substring(8, 9)), string.substring(9, string.length));
        }
        static createMessageRole(messageRolePrefix) {
            switch (messageRolePrefix) {
                case Package.TARGET_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.TARGET_MESSAGE;
                case Package.STANDARD_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.STANDARD_MESSAGE;
                case Package.REPLY_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.REPLY_MESSAGE;
                case Package.ERROR_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.ERROR_MESSAGE;
                default:
                    throw new Error('The given message role prefix is not valid.');
            }
        }
        getIndex() {
            return this.index;
        }
        getMessage() {
            return this.message;
        }
        getMessageRole() {
            return this.messageRole;
        }
        hasIndex(index) {
            return this.index === index;
        }
        toString() {
            return (this.getIndexString() + this.getMessageRolePrefix() + this.message);
        }
        getIndexString() {
            var indexString = this.index.toString();
            while (indexString.length < Package.INDEX_STRING_LENGTH) {
                indexString = '0' + indexString;
            }
            return indexString;
        }
        getMessageRolePrefix() {
            switch (this.messageRole) {
                case MessageRole_1.MessageRole.TARGET_MESSAGE:
                    return Package.TARGET_MESSAGE_PREFIX;
                case MessageRole_1.MessageRole.STANDARD_MESSAGE:
                    return Package.STANDARD_MESSAGE_PREFIX;
                case MessageRole_1.MessageRole.REPLY_MESSAGE:
                    return Package.REPLY_MESSAGE_PREFIX;
                case MessageRole_1.MessageRole.ERROR_MESSAGE:
                    return Package.ERROR_MESSAGE_PREFIX;
                default:
                    throw new Error('There is not defined a prefix for the given message role.');
            }
        }
    }
    Package.TARGET_MESSAGE_PREFIX = 'T';
    Package.STANDARD_MESSAGE_PREFIX = 'M';
    Package.REPLY_MESSAGE_PREFIX = 'R';
    Package.ERROR_MESSAGE_PREFIX = 'E';
    Package.INDEX_STRING_LENGTH = 8;
    exports.Package = Package;
});
define("Core/Net/EndPoint2/NetEndPoint2", ["require", "exports", "Core/Container/LinkedList/LinkedList", "Core/Net/EndPoint2/MessageRole", "Core/Net/EndPoint/NetEndPoint", "Core/Net/EndPoint2/Package"], function (require, exports, LinkedList_3, MessageRole_2, NetEndPoint_1, Package_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint2 {
        constructor(ip, port, optionalTarget, webSocketType) {
            this.messageIndex = 0;
            this.receivedPackages = new LinkedList_3.LinkedList();
            this.receive = (message) => {
                this.receivePackage(Package_1.Package.createFromString(message));
            };
            this.internalNetEndPoint = new NetEndPoint_1.NetEndPoint(ip, port, optionalTarget, webSocketType);
            this.internalNetEndPoint.setReceiver(this.receive);
        }
        getTarget() {
            return this.internalNetEndPoint.getTarget();
        }
        hasReceiver() {
            return (this.receiver !== undefined);
        }
        hasTarget() {
            return this.internalNetEndPoint.hasTarget();
        }
        sendAndGetReply(message) {
            const messageIndex = this.getNextMessageIndex();
            this.sendPackage(new Package_1.Package(messageIndex, MessageRole_2.MessageRole.STANDARD_MESSAGE, message));
            return 'Ok';
        }
        setReceiver(receiver) {
            if (receiver === null) {
                throw new Error('The given receiver is null.');
            }
            if (receiver === undefined) {
                throw new Error('The given receiver is null');
            }
            this.receiver = receiver;
        }
        getNextMessageIndex() {
            this.messageIndex++;
            return this.messageIndex;
        }
        receivePackage(package_) {
            switch (package_.getMessageRole()) {
                case MessageRole_2.MessageRole.STANDARD_MESSAGE:
                    if (this.receiver === undefined) {
                        throw new Error('The current NetEndPoint3 does not have a receiver.');
                    }
                    try {
                        this.sendPackage(new Package_1.Package(package_.getIndex(), MessageRole_2.MessageRole.REPLY_MESSAGE, this.receiver(package_.getMessage())));
                    }
                    catch (error) {
                        this.sendPackage(new Package_1.Package(package_.getIndex(), MessageRole_2.MessageRole.ERROR_MESSAGE, 'ERROR_MESSAGE'));
                    }
                    break;
                case MessageRole_2.MessageRole.REPLY_MESSAGE:
                case MessageRole_2.MessageRole.ERROR_MESSAGE:
                    this.receivedPackages.addAtEnd(package_);
                    break;
                default:
                    throw new Error('The received package is not valid.');
            }
        }
        sendPackage(package_) {
            this.internalNetEndPoint.send(package_.toString());
        }
        waitToAndGetAndRemoveReceivedPackage(index) {
            const startTimeInMilliseconds = new Date().getMilliseconds();
            while (new Date().getMilliseconds() - startTimeInMilliseconds < NetEndPoint2.TIMEOUT_IN_MILLISECONDS) {
                const package_ = this.receivedPackages.getRefFirstByConditionOrNull(rp => rp.hasIndex(index));
                if (package_ !== null) {
                    this.receivedPackages.removeFirst2(package_);
                    return package_;
                }
            }
            throw new Error('The current NetEndPoint3 reached the timeout by waiting to a message.');
        }
    }
    NetEndPoint2.TIMEOUT_IN_MILLISECONDS = 5000;
    exports.NetEndPoint2 = NetEndPoint2;
});
define("Core/Net/EndPoint3/IDataProviderController", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("Core/Net/EndPoint3/Protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Protocol {
    }
    Protocol.COMMANDS_HEADER = 'Commands';
    Protocol.DONE_HEADER = 'Done';
    Protocol.ERROR_HEADER = 'Error';
    Protocol.MULTI_DATA_HEADER = 'MultiData';
    Protocol.MULTI_DATA_REQUEST_HEADER = 'MultiDataRequest';
    exports.Protocol = Protocol;
});
define("Core/Net/EndPoint3/NetEndPoint3", ["require", "exports", "Core/Document/ChainedNode/ChainedNode", "Core/Container/LinkedList/LinkedList", "Core/Net/EndPoint2/NetEndPoint2", "Core/Document/Node/Node", "Core/Net/EndPoint3/Protocol"], function (require, exports, ChainedNode_1, LinkedList_4, NetEndPoint2_1, Node_2, Protocol_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint3 {
        constructor(ip, port, optionalTarget, webSocketType) {
            this.receiveMessageAndGetReply = (message) => {
                try {
                    return this.receiveDocumentNodeMessageAndGetReply(ChainedNode_1.ChainedNode.fromString(message));
                }
                catch (error) {
                    if (error === undefined || error == null) {
                        return (Protocol_2.Protocol.ERROR_HEADER + Node_2.Node.withHeader('An error occured.'));
                    }
                    if (error instanceof Error) {
                        return (Protocol_2.Protocol.ERROR_HEADER + Node_2.Node.withHeader(error.message));
                    }
                    return (Protocol_2.Protocol.ERROR_HEADER + Node_2.Node.withHeader('An error occured: ' + error));
                }
            };
            this.internalNetEndPoint = new NetEndPoint2_1.NetEndPoint2(ip, port, optionalTarget, webSocketType);
            this.internalNetEndPoint.setReceiver(this.receiveMessageAndGetReply);
        }
        getData(request) {
            const requests = LinkedList_4.LinkedList.withElement(request);
            return this.getMultiData(requests).getRefOne();
        }
        getMultiData(requests) {
            const message = Protocol_2.Protocol.MULTI_DATA_REQUEST_HEADER + requests.toStringInBrackets();
            const reply = Node_2.Node.fromString(this.internalNetEndPoint.sendAndGetReply(message));
            switch (reply.getHeader()) {
                case Protocol_2.Protocol.MULTI_DATA_HEADER:
                    return reply.getRefAttributes();
                case Protocol_2.Protocol.ERROR_HEADER:
                    throw new Error(reply.getOneAttributeHeader());
                default:
                    throw new Error('The given reply is not valid.');
            }
        }
        getTarget() {
            return this.internalNetEndPoint.getTarget();
        }
        hasReceiverController() {
            return (this.receiverController !== undefined);
        }
        run(command) {
            const commands = new LinkedList_4.LinkedList();
            commands.addAtEnd(command);
            this.runCommands(commands);
        }
        runCommands(commands) {
            const message = Protocol_2.Protocol.COMMANDS_HEADER + commands.toStringInBrackets();
            this.internalNetEndPoint.sendAndGetReply(message);
        }
        setReceiverController(receiverController) {
            if (receiverController === null) {
                throw new Error('The given receiverController is null.');
            }
            if (receiverController === undefined) {
                throw new Error('The given receiverController is undefined.');
            }
            this.receiverController = receiverController;
        }
        getReceiverController() {
            return this.receiverController;
        }
        receiveDocumentNodeMessageAndGetReply(message) {
            const receiverController = this.getReceiverController();
            switch (message.getHeader()) {
                case Protocol_2.Protocol.COMMANDS_HEADER:
                    for (const a of message.getAttributes()) {
                        receiverController.run(a);
                    }
                    return Protocol_2.Protocol.DONE_HEADER;
                case Protocol_2.Protocol.MULTI_DATA_REQUEST_HEADER:
                    return (Protocol_2.Protocol.MULTI_DATA_HEADER + '(' + receiverController.getMultiData(message.getAttributes()) + ')');
                default:
                    throw new Error('The given message is not valid.');
            }
        }
    }
    exports.NetEndPoint3 = NetEndPoint3;
});
define("Core/ProgramAtom/Name/PascalCaseCatalogue", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class PascalCaseCatalogue {
        constructor() { }
    }
    PascalCaseCatalogue.CURSOR_POSITION = 'CursorPosition';
    PascalCaseCatalogue.HEIGHT = 'Height';
    PascalCaseCatalogue.SIZE = 'Size';
    PascalCaseCatalogue.WIDTH = 'Width';
    exports.PascalCaseCatalogue = PascalCaseCatalogue;
});
define("Core/ProgramStructure/Caching/CachingContainer", ["require", "exports", "Core/Container/LinkedList/LinkedList", "Core/Container/Pair/Pair"], function (require, exports, LinkedList_5, Pair_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CachingContainer {
        constructor() {
            this.elements = new LinkedList_5.LinkedList();
        }
        contains(element) {
            return this.elements.contains(e => Object.is(e, element));
        }
        containsWithId(id) {
            return this.elements.contains(e => e.getRefElement1() === id);
        }
        getRefById(id) {
            return this.elements.getRefFirstByCondition(e => e.getRefElement1() === id).getRefElement2();
        }
        registerAtId(id, element) {
            console.log('The current CachingContainer registers an element at the given id \'' + id + '\'.');
            if (id === null) {
                throw new Error('The given id is null.');
            }
            if (id === undefined) {
                throw new Error('The given id is undefined.');
            }
            if (id.length === 0) {
                throw new Error('The given id is empty.');
            }
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.assertDoesNotContainId(id);
            this.assertDoesNotContain(element);
            this.elements.addAtEnd(new Pair_1.Pair(id, element));
        }
        assertDoesNotContain(element) {
            if (this.contains(element)) {
                throw new Error('The current CachingContainer contains already the given element.');
            }
        }
        assertDoesNotContainId(id) {
            if (this.containsWithId(id)) {
                throw new Error('The current CachingContainer contains already an element with the given id.');
            }
        }
    }
    exports.CachingContainer = CachingContainer;
});
define("Core/Testing/BaseTest/BaseTest", ["require", "exports", "Core/Container/LinkedList/LinkedList"], function (require, exports, LinkedList_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BaseTest {
        constructor() {
            this.currentTestCaseErrors = new LinkedList_6.LinkedList();
        }
        run() {
            console.log('Start ' + this.constructor.name);
            for (const tc of this.getRefTestCases()) {
                this.runTestCase(tc);
            }
            console.log();
        }
        addErrorForCurrentTestCase(error) {
            this.currentTestCaseErrors.addAtEnd(error);
        }
        getRefProperties() {
            var properties = [];
            var prototype = this;
            do {
                properties = properties.concat(Object.getOwnPropertyNames(prototype));
            } while (prototype = Object.getPrototypeOf(prototype));
            return properties;
        }
        getRefTestCases() {
            var testCases = [];
            for (const p of this.getRefProperties()) {
                const name = p;
                if (name.startsWith('testCase_')) {
                    testCases = testCases.concat(name);
                }
            }
            return testCases;
        }
        runTestCase(testCase) {
            this.currentTestCaseErrors.clear();
            try {
                this[testCase]();
                if (this.currentTestCaseErrors.isEmpty()) {
                    console.log('   PASSED: ' + testCase);
                }
                else {
                    console.log('-->FAILED: ' + testCase);
                }
            }
            catch (error) {
                console.log('-->FAILED: ' + testCase + ': ' + error);
            }
            var i = 1;
            for (const e of this.currentTestCaseErrors) {
                console.log('   ' + i.toString() + ') ' + e);
                i++;
            }
        }
    }
    exports.BaseTest = BaseTest;
});
define("Core/Testing/Test/NumberMediator", ["require", "exports", "Core/Testing/Test/Mediator"], function (require, exports, Mediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NumberMediator extends Mediator_1.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
    }
    exports.NumberMediator = NumberMediator;
});
define("Core/Testing/Test/StringMediator", ["require", "exports", "Core/Testing/Test/Mediator"], function (require, exports, Mediator_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class StringMediator extends Mediator_2.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
        isNotEmpty() {
            if (this.argument.length < 1) {
                this.test.addErrorForCurrentTestCase('A string, that is not empty, was expected, but an empty string was received.');
            }
        }
    }
    exports.StringMediator = StringMediator;
});
define("Core/Testing/Test/Test", ["require", "exports", "Core/Testing/BaseTest/BaseTest", "Core/Testing/Test/FunctionMediator", "Core/Testing/Test/Mediator", "Core/Testing/Test/NumberMediator", "Core/Testing/Test/StringMediator"], function (require, exports, BaseTest_1, FunctionMediator_1, Mediator_3, NumberMediator_1, StringMediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Test extends BaseTest_1.BaseTest {
        expect(argument) {
            if (!argument) {
                this.addErrorForCurrentTestCase('True was expected, but false was received.');
            }
        }
        expectFunction(argument) {
            return new FunctionMediator_1.FunctionMediator(this, argument);
        }
        expectNot(argument) {
            if (argument) {
                this.addErrorForCurrentTestCase('False was expected, but true was received.');
            }
        }
        expectNotThisCase() {
            this.addErrorForCurrentTestCase('The current case was not expected, but reached.');
        }
        expectNumber(argument) {
            return new NumberMediator_1.NumberMediator(this, argument);
        }
        expectObject(argument) {
            return new Mediator_3.Mediator(this, argument);
        }
        expectString(argument) {
            return new StringMediator_1.StringMediator(this, argument);
        }
    }
    exports.Test = Test;
});
define("Core/Testing/Test/Mediator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Mediator {
        constructor(test, argument) {
            if (test === null) {
                throw new Error('The given test is null.');
            }
            if (test === undefined) {
                throw new Error('The given test is undefined.');
            }
            this.test = test;
            this.argument = argument;
        }
        equals(value) {
            if (this.argument !== value) {
                this.test.addErrorForCurrentTestCase('An object that equals ' + value.toString() + ' was expected, but ' + this.argument.toString() + ' was received.');
            }
        }
        fulfills(condition) {
            if (!condition(this.argument)) {
                this.test.addErrorForCurrentTestCase('An object, that fulfills the given condition, was expected, but an object, that does not fulfill it, was received.');
            }
        }
        hasStringRepresentation(string) {
            if (string === null) {
                throw new Error('The given string is null.');
            }
            if (string === undefined) {
                throw new Error('The given string is undefined.');
            }
            const actualString = this.argument.toString();
            if (actualString !== string) {
                this.test.addErrorForCurrentTestCase('An object with the string representation \''
                    + string
                    + '\' was expected, but an object with the string representation \''
                    + actualString
                    + '\' was received.');
            }
        }
        isNotNullOrUndefined() {
            if (this.argument === null) {
                this.test.addErrorForCurrentTestCase('An object was expected, but null was received.');
            }
            if (this.argument === undefined) {
                this.test.addErrorForCurrentTestCase('An object was exected, but undefined was received.');
            }
        }
        isSameAs(object) {
            if (!Object.is(this.argument, object)) {
                this.test.addErrorForCurrentTestCase('The given object was expected, but another object was received.');
            }
        }
    }
    exports.Mediator = Mediator;
});
define("Core/Testing/Test/ThrownErrorMediator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ThrownErrorMediator {
        constructor(test, error) {
            if (test === null) {
                throw new Error('The given test is null.');
            }
            if (test === undefined) {
                throw new Error('The given test is undefined.');
            }
            if (error === null) {
                throw new Error('The given error is null.');
            }
            if (error === undefined) {
                throw new Error('The given error is undefined.');
            }
            this.test = test;
            this.error = error;
        }
        withMessage(message) {
            if (message === null) {
                throw new Error('The given message is null.');
            }
            if (message === undefined) {
                throw new Error('The given message is undefined.');
            }
            if (this.error.message !== message) {
                this.test.addErrorForCurrentTestCase('An error with the message \''
                    + message
                    + '\' was expected, but an error with the message \''
                    + this.error.message
                    + '\' was received.');
            }
        }
    }
    exports.ThrownErrorMediator = ThrownErrorMediator;
});
define("Core/Testing/Test/FunctionMediator", ["require", "exports", "Core/Testing/Test/Mediator", "Core/Testing/Test/ThrownErrorMediator"], function (require, exports, Mediator_4, ThrownErrorMediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FunctionMediator extends Mediator_4.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
        doesNotThrowError() {
            try {
                this.argument();
            }
            catch (error) {
                this.test.addErrorForCurrentTestCase('There was not expected any error, but there was thrown an error.');
            }
        }
        throwsError() {
            try {
                this.argument();
                this.test.addErrorForCurrentTestCase('An error was expected, but there was not thrown any error.');
            }
            catch (error) {
                return new ThrownErrorMediator_1.ThrownErrorMediator(this.test, error);
            }
        }
    }
    exports.FunctionMediator = FunctionMediator;
});
define("Core/Testing/Test/TestPool", ["require", "exports", "Core/Container/LinkedList/LinkedList"], function (require, exports, LinkedList_7) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TestPool {
        constructor() {
            this.tests = new LinkedList_7.LinkedList();
            this.testPools = new LinkedList_7.LinkedList();
        }
        run() {
            this.tests.forEach(t => t.run());
            this.testPools.forEach(tp => tp.run());
        }
        addTest(test) {
            this.tests.addAtEnd(test);
        }
        addTestPool(testPool) {
            if (testPool.containsRecursively(this)) {
                throw new Error('The given test pool contains the current test pool recursively.');
            }
            this.testPools.addAtEnd(testPool);
        }
        containsRecursively(testPool) {
            if (this.testPools.containsGivenElement(testPool)) {
                return true;
            }
            return this.testPools.contains(ts => ts.containsRecursively(testPool));
        }
    }
    exports.TestPool = TestPool;
});
define("Core/Web/CookieManager", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CookieManager {
        CookieManager() { }
        deleteCookieByName(name) {
            document.cookie = 'name=' + name + '; max-age=0';
        }
        getCookieValueByCookieNameOrEmptyString(cookieName) {
            const cookieString = window.document.cookie;
            const cookies = cookieString.split(';');
            for (const c of cookies) {
                if (c.split('=')[0] === cookieName) {
                    return c.split('=')[1];
                }
            }
            return '';
        }
        setOrAddCookieWithNameAndValue(name, value) {
            window.document.cookie = name + '=' + value + '; max-age=' + CookieManager.COOKIE_MAX_AGE_IN_SECONDS;
        }
    }
    CookieManager.COOKIE_MAX_AGE_IN_SECONDS = 31536000;
    CookieManager.INSTANCE = new CookieManager();
    exports.CookieManager = CookieManager;
});
define("Core/Web/URLLineReader", ["require", "exports", "Core/Container/SingleContainer/SingleContainer"], function (require, exports, SingleContainer_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class URLLineReader {
        constructor() { }
        getOptionalValueOfURLParameterByName(name) {
            const parameterString = window.location.search;
            const localURLSearchParams = new URLSearchParams(parameterString);
            const value = localURLSearchParams.get(name);
            if (value === null) {
                return SingleContainer_1.SingleContainer.withoutElement();
            }
            return SingleContainer_1.SingleContainer.withElement(value);
        }
    }
    URLLineReader.INSTANCE = new URLLineReader();
    exports.URLLineReader = URLLineReader;
});
define("System/Application/WebApplicationProtocol/CommandProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CommandProtocol {
    }
    CommandProtocol.DELETE_COOKIE_BY_NAME = "DeleteCookieByName";
    CommandProtocol.OPEN_NEW_TAB = 'OpenNewTab';
    CommandProtocol.REDIRECT = "Redirect";
    CommandProtocol.SET_CSS = "SetCSS";
    CommandProtocol.SET_EVENT_FUNCTIONS = 'SetEventFunctions';
    CommandProtocol.SET_FILE = 'SetFile';
    CommandProtocol.SET_HTML_ELEMENT = "SetHTMLElement";
    CommandProtocol.SET_ICON = 'SetIcon';
    CommandProtocol.SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE = 'SetOrAddCookieWithNameAndValue';
    CommandProtocol.SET_ROOT_HTML_ELEMENT = 'SetRootHTMLElement';
    CommandProtocol.SET_TITLE = 'SetTitle';
    CommandProtocol.SET_USER_INPUT_FUNCTIONS = 'SetUserInputFunctions';
    CommandProtocol.SET_USER_INPUTS = 'SetUserInputs';
    CommandProtocol.WRITE_TEXT_TO_CLIPBOARD = 'WriteTextToClipBoard';
    exports.CommandProtocol = CommandProtocol;
});
define("System/FrontendWebGUI/EventFunction", ["require", "exports", "Core/Document/ChainedNode/ChainedNode"], function (require, exports, ChainedNode_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class EventFunction {
        static fromSpecification(specification) {
            const lHTMLElementId = specification.getRefAttributeAtIndex(1).getHeader();
            const lHTMLEventName = specification.getRefAttributeAtIndex(2).getHeader();
            return this.withHTMLElementIdAndHtmlEventName(lHTMLElementId, lHTMLEventName);
        }
        static withHTMLElementIdAndHtmlEventName(pHTMLElementId, pHTMLEventName) {
            return new EventFunction(pHTMLElementId, pHTMLEventName);
        }
        constructor(pHTMLElementId, pHTMLEventName) {
            if (pHTMLElementId === null) {
                throw new Error('The given HTMLElementId is null.');
            }
            if (pHTMLElementId === undefined) {
                throw new Error('The given HTMLElementId is undefined.');
            }
            if (pHTMLEventName === null) {
                throw new Error('The given HTMLEventName is null.');
            }
            if (pHTMLEventName === undefined) {
                throw new Error('The given HTMLEventName is undefined.');
            }
            this.mHTMLElementId = pHTMLElementId;
            this.mHTMLEventName = pHTMLEventName;
        }
        getHTMLElementId() {
            return this.mHTMLElementId;
        }
        getHTMLEventName() {
            return this.mHTMLEventName;
        }
        toGUICommand() {
            return ChainedNode_2.ChainedNode.fromString('GUI.ControlByFixedId(' + this.getHTMLElementId() + ').RunHTMLEvent(' + this.getHTMLEventName() + ')');
        }
    }
    exports.EventFunction = EventFunction;
});
define("SystemAPI/GraphicAPI/ImageAPI/IImage", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("SystemAPI/FrontendWebGUIAPI/IFrontendWebGUI", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("System/Element/Element", ["require", "exports", "Core/Document/Node/Node"], function (require, exports, Node_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Element {
        getSpecification() {
            return this.getSpecificationAs(this.getType());
        }
        getSpecificationAs(type) {
            return Node_3.Node.withHeaderAndAttributes(type, this.getAttributes());
        }
        toString() {
            return this.getSpecification().toString();
        }
    }
    exports.Element = Element;
});
define("System/FrontendWebGUI/UserInput", ["require", "exports", "System/Element/Element", "Core/Container/LinkedList/LinkedList", "Core/Document/Node/Node"], function (require, exports, Element_1, LinkedList_8, Node_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class UserInput extends Element_1.Element {
        constructor(pHTMLElementId, userInput) {
            super();
            if (pHTMLElementId === null) {
                throw new Error('The given pHTMLElementId is null.');
            }
            if (pHTMLElementId === undefined) {
                throw new Error('The given pHTMLElementId is undefined.');
            }
            if (userInput === null) {
                throw new Error('The given userInput is null.');
            }
            if (userInput === undefined) {
                throw new Error('The given userInput is undefined.');
            }
            this.mHTMLElementId = pHTMLElementId;
            this.userInput = userInput;
        }
        static withHTMLElementIdAndUserInput(pHTMLElementId, userInput) {
            return new UserInput(pHTMLElementId, userInput);
        }
        getAttributes() {
            const attributes = new LinkedList_8.LinkedList();
            attributes.addAtEnd(Node_4.Node.withHeader(this.getHTMLElementId()));
            const userInput = this.getUserInput();
            if (userInput.length === 0) {
                attributes.addAtEnd(new Node_4.Node());
            }
            else {
                attributes.addAtEnd(Node_4.Node.withHeader(userInput));
            }
            return attributes;
        }
        getHTMLElementId() {
            return this.mHTMLElementId;
        }
        getType() {
            return UserInput.TYPE_HEADER;
        }
        getUserInput() {
            return this.userInput;
        }
    }
    UserInput.TYPE_HEADER = 'UserInput';
    exports.UserInput = UserInput;
});
define("System/FrontendWebGUI/UserInputFunction", ["require", "exports", "System/FrontendWebGUI/UserInput"], function (require, exports, UserInput_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class UserInputFunction {
        static fromSpecification(specification) {
            const lHTMLElementId = specification.getRefAttributeAtIndex(1).getHeader();
            const userInputFunctionString = specification.getRefAttributeAtIndex(2).getHeader();
            return this.withHTMLElementIdAndUserInputFunctionFromString(lHTMLElementId, userInputFunctionString);
        }
        static withHTMLElementIdAndUserInputFunctionFromString(pHTMLElementId, userInputFunctionString) {
            return new UserInputFunction(pHTMLElementId, Function('x', userInputFunctionString));
        }
        constructor(pHTMLElementId, userInputFunction) {
            if (pHTMLElementId === null) {
                throw new Error('The given pHTMLElementId is null.');
            }
            if (pHTMLElementId === undefined) {
                throw new Error('The given pHTMLElementId is undefined.');
            }
            if (userInputFunction === null) {
                throw new Error('The given userInputFunction is null.');
            }
            if (userInputFunction === undefined) {
                throw new Error('The given userInputFunction is undefined.');
            }
            this.mHTMLElementId = pHTMLElementId;
            this.userInputFunction = userInputFunction;
        }
        getHTMLElementId() {
            return this.mHTMLElementId;
        }
        getUserInputOrNullUsingDocument(document) {
            const pHTMLElement = document.getElementById(this.getHTMLElementId());
            if (pHTMLElement === null) {
                return null;
            }
            const userInput = this.userInputFunction(pHTMLElement);
            return UserInput_1.UserInput.withHTMLElementIdAndUserInput(this.getHTMLElementId(), userInput);
        }
    }
    exports.UserInputFunction = UserInputFunction;
});
define("System/FrontendWebGUI/FrontendWebGUI", ["require", "exports", "Core/Container/LinkedList/LinkedList"], function (require, exports, LinkedList_9) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontendWebGUI {
        constructor(eventTaker, fileTaker, window) {
            this.userInputFunctions = new LinkedList_9.LinkedList();
            if (eventTaker === null) {
                throw new Error('The given event taker is null.');
            }
            if (eventTaker === undefined) {
                throw new Error('The given event taker is undefined.');
            }
            if (fileTaker === null) {
                throw new Error('The given file taker is null.');
            }
            if (fileTaker === undefined) {
                throw new Error('The given file taker is undefined.');
            }
            if (window === null) {
                throw new Error('The given window is null.');
            }
            if (window === undefined) {
                throw new Error('The given window is undefined.');
            }
            this.eventTaker = eventTaker;
            this.fileTaker = fileTaker;
            this.window = window;
            this.style = this.window.document.createElement('style');
            this.window.document.head.appendChild(this.style);
        }
        static withEventTakerAndFileTakerAndWindow(eventTaker, fileTaker, window) {
            return new FrontendWebGUI(eventTaker, fileTaker, window);
        }
        getIcon() {
            return this.icon;
        }
        getTitle() {
            return this.title;
        }
        getUserInputs() {
            const userInputs = new LinkedList_9.LinkedList();
            for (const uif of this.userInputFunctions) {
                const userInput = uif.getUserInputOrNullUsingDocument(this.window.document);
                if (userInput !== null) {
                    userInputs.addAtEnd(userInput);
                }
            }
            return userInputs;
        }
        openNewTabWithURL(pURL) {
            if (!pURL.startsWith('http://') && !pURL.startsWith('https://')) {
                pURL = 'https://' + pURL;
            }
            console.log('The current CanvasGUI opens a new tab with the URL \'' + pURL + '\'');
            this.window.open(pURL, '_blank');
        }
        redirectTo(pURL) {
            if (!pURL.startsWith('http://') && !pURL.startsWith('https://')) {
                pURL = 'https://' + pURL;
            }
            console.log('The current CanvasGUI redirects to \'' + pURL + '\'.');
            this.window.open(pURL, '_self');
        }
        setCSS(pCSS) {
            this.style.innerHTML = pCSS;
        }
        setEventFunctions(eventFunctions) {
            for (const ef of eventFunctions) {
                const lHTMLElement = this.window.document.getElementById(ef.getHTMLElementId());
                if (lHTMLElement !== null) {
                    const command = ef.toGUICommand();
                    lHTMLElement[ef.getHTMLEventName()] = () => this.takeEvent(command);
                }
            }
            this.setupAsyncEventFunctions();
        }
        setHTMLElementFromString(paramHTMLElementId, paramHTMLElementAsString) {
            const localHTMLElement = this.getOriHTMLElementById(paramHTMLElementId);
            localHTMLElement.outerHTML = paramHTMLElementAsString;
        }
        setIcon(icon) {
            if (icon === null) {
                throw new Error('The given icon is null.');
            }
            if (icon === undefined) {
                throw new Error('The given icon is undefined.');
            }
            const iconHTMLElement = this.window.document.getElementById('icon');
            iconHTMLElement.href = icon.toCanvas().toDataURL('image/png');
        }
        setRootHTMLElementFromString(rootHTMLElementAsString) {
            const rootElement = this.getRefRootElement();
            rootElement.outerHTML = rootHTMLElementAsString;
        }
        setTitle(title) {
            if (title === null) {
                throw new Error('The given title is null.');
            }
            if (title === undefined) {
                throw new Error('The given title is undefined.');
            }
            if (title.length == 0) {
                throw new Error('The given title is empty.');
            }
            this.title = title;
            this.window.document.title = this.title;
        }
        setUserInputFunctions(userInputFunctions) {
            if (userInputFunctions === null) {
                throw new Error('The given userInputFunctions is null.');
            }
            if (userInputFunctions === undefined) {
                throw new Error('The given userInputFunctions is undefined.');
            }
            this.userInputFunctions = userInputFunctions;
        }
        getOriHTMLElementById(paramHTMLElementId) {
            return this.window.document.getElementById(paramHTMLElementId);
        }
        getRefRootElement() {
            var rootElement = this.window.document.getElementById('root');
            if (rootElement === null) {
                rootElement = this.window.document.createElement('div');
                rootElement.id = 'root';
                this.window.document.body.appendChild(rootElement);
            }
            return rootElement;
        }
        setupAsyncEventFunctions() {
            for (const e of this.window.document.children) {
                this.setupAsyncEventFunctionsOfElement(e);
            }
        }
        setupAsyncEventFunctionsOfElement(element) {
            if (element.hasAttribute('data-uploader')) {
                const uploader = element;
                uploader.onchange = () => {
                    if (uploader.files.length > 0) {
                        const file = uploader.files[0];
                        const fileReader = new FileReader();
                        fileReader.readAsDataURL(file);
                        fileReader.onloadend = () => {
                            if (fileReader.readyState === FileReader.DONE) {
                                this.takeFile(element.id, fileReader.result);
                            }
                        };
                    }
                };
            }
            for (const e of element.children) {
                this.setupAsyncEventFunctionsOfElement(e);
            }
        }
        takeEvent(command) {
            this.eventTaker(command);
        }
        takeFile(controlFixedId, file) {
            this.fileTaker(controlFixedId, file);
        }
    }
    exports.FrontendWebGUI = FrontendWebGUI;
});
define("System/Graphic/Color/Color", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Color {
        constructor(redValue, greenValue, blueValue, alphaValue) {
            if (redValue < 0 || redValue > 255) {
                throw new Error('The given redValue is not valid.');
            }
            if (greenValue < 0 || greenValue > 255) {
                throw new Error('The given greenValue is not valid.');
            }
            if (blueValue < 0 || blueValue > 255) {
                throw new Error('The given blueValue is not valid.');
            }
            if (alphaValue < 0 || alphaValue > 255) {
                throw new Error('The given blueValue is not valid.');
            }
            this.redValue = redValue;
            this.greenValue = greenValue;
            this.blueValue = blueValue;
            this.alphaValue = alphaValue;
        }
        static fromSpecification(specification) {
            return Color.fromString(specification.getOneAttributeHeader());
        }
        static fromString(string) {
            if (string === null) {
                throw new Error('The given string is null.');
            }
            if (string === undefined) {
                throw new Error('The given string is undefined.');
            }
            switch (string.length) {
                case 8:
                    return new Color(Number.parseInt('0x' + string.substr(2, 2)), Number.parseInt('0x' + string.substr(4, 2)), Number.parseInt('0x' + string.substr(6, 2)), 255);
                case 10:
                    return new Color(Number.parseInt('0x' + string.substr(2, 2)), Number.parseInt('0x' + string.substr(4, 2)), Number.parseInt('0x' + string.substr(6, 2)), Number.parseInt('0x' + string.substr(8, 2)));
                default:
                    throw new Error('The given string is not valid.');
            }
        }
        getAlphaValue() {
            return this.alphaValue;
        }
        getBlueValue() {
            return this.blueValue;
        }
        getGreenValue() {
            return this.greenValue;
        }
        getHTMLCode() {
            return '#'
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.redValue)
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.greenValue)
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.blueValue);
        }
        getRedValue() {
            return this.redValue;
        }
        getNumberAsHexadecimalStringWithLeadingZeros(number) {
            var string = number.toString(16).toUpperCase();
            while (string.length < 2) {
                string = '0' + string;
            }
            return string;
        }
    }
    Color.BLACK = new Color(0, 0, 0, 255);
    Color.WHITE = new Color(255, 255, 255, 255);
    exports.Color = Color;
});
define("System/Graphic/Image/Image", ["require", "exports", "System/Graphic/Color/Color", "System/Element/Element", "Core/Container/Matrix/Matrix", "Core/Document/Node/Node", "Core/ProgramAtom/Name/PascalCaseCatalogue"], function (require, exports, Color_1, Element_2, Matrix_1, Node_5, PascalCaseCatalogue_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Image extends Element_2.Element {
        constructor(pixels) {
            super();
            this.pixels = pixels;
        }
        static fromSpecification(specification) {
            const pixels = new Matrix_1.Matrix();
            const width = specification.getRefFirstAttributeWithHeader(PascalCaseCatalogue_1.PascalCaseCatalogue.WIDTH).getOneAttributeAsNumber();
            var row = new Array();
            var i = 1;
            for (const a of specification.getRefFirstAttributeWithHeader(Image.PIXEL_ARRAY_HEADER).getRefAttributes()) {
                row.push(Color_1.Color.fromSpecification(Node_5.Node.withAttribute(a)));
                i++;
                if (i > width) {
                    pixels.addRow(row);
                    row = new Array();
                    i = 1;
                }
            }
            return new Image(pixels);
        }
        getAttributes() {
            throw new Error("Method not implemented.");
        }
        getHeight() {
            return this.pixels.getRowCount();
        }
        getPixelAtIndex(index) {
            return this.pixels.getRefAt(index);
        }
        getPixelAtPosition(xPosition, yPosition) {
            return this.pixels.getRefAtRowAndColumn(xPosition, yPosition);
        }
        getSizeInPixel() {
            return (this.getWidth() * this.getHeight());
        }
        getType() {
            return Image.TYPE_HEADER;
        }
        getWidth() {
            return this.pixels.getColumnCount();
        }
        toCanvas() {
            this.generateCanvasIfNeeded();
            return this.canvas;
        }
        generateCanvasIfNeeded() {
            if (this.generatingCanvasIsNeeded()) {
                this.generateCanvasWhenNeeded();
            }
        }
        generateCanvasWhenNeeded() {
            const width = this.getWidth();
            const height = this.getHeight();
            this.canvas = document.createElement('canvas');
            this.canvas.width = width;
            this.canvas.height = height;
            const context = this.canvas.getContext('2d');
            for (var rowIndex = 1; rowIndex <= height; rowIndex++) {
                for (var columnIndex = 1; columnIndex <= width; columnIndex++) {
                    const pixel = this.getPixelAtPosition(rowIndex, columnIndex);
                    context.fillStyle = pixel.getHTMLCode();
                    context.fillRect(columnIndex - 1, rowIndex - 1, 1, 1);
                }
            }
        }
        generatingCanvasIsNeeded() {
            return (this.canvas === undefined);
        }
    }
    Image.TYPE_HEADER = 'Image';
    Image.PIXEL_ARRAY_HEADER = 'PixelArray';
    exports.Image = Image;
});
define("System/Application/WebApplication/FrontendWebClientGUIManager", ["require", "exports", "System/Application/WebApplicationProtocol/CommandProtocol", "System/FrontendWebGUI/FrontendWebGUI", "System/Graphic/Image/Image", "System/FrontendWebGUI/UserInputFunction", "System/FrontendWebGUI/EventFunction"], function (require, exports, CommandProtocol_1, FrontendWebGUI_1, Image_1, UserInputFunction_1, EventFunction_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontendWebClientGUIManager {
        static withEventTakerAndFileTakerAndWindow(eventTaker, fileTaker, window) {
            return new FrontendWebClientGUIManager(eventTaker, fileTaker, window);
        }
        constructor(eventTaker, fileTaker, window) {
            this.mFrontendWebGUI = FrontendWebGUI_1.FrontendWebGUI.withEventTakerAndFileTakerAndWindow(eventTaker, fileTaker, window);
        }
        getUserInputs() {
            return this.mFrontendWebGUI.getUserInputs();
        }
        openNewTabWithURL(pURL) {
            this.mFrontendWebGUI.openNewTabWithURL(pURL);
        }
        redirectTo(pURL) {
            this.mFrontendWebGUI.redirectTo(pURL);
        }
        runGUICommand(pGUICommand) {
            switch (pGUICommand.getHeader()) {
                case CommandProtocol_1.CommandProtocol.SET_TITLE:
                    this.mFrontendWebGUI.setTitle(pGUICommand.getOneAttribute().getHeader());
                    break;
                case CommandProtocol_1.CommandProtocol.SET_ICON:
                    const icon = Image_1.Image.fromSpecification(pGUICommand.getOneAttributeAsNode());
                    this.mFrontendWebGUI.setIcon(icon);
                    break;
                case CommandProtocol_1.CommandProtocol.SET_ROOT_HTML_ELEMENT:
                    this.mFrontendWebGUI.setRootHTMLElementFromString(pGUICommand.getOneAttribute().getHeader());
                    break;
                case CommandProtocol_1.CommandProtocol.SET_HTML_ELEMENT:
                    const localHTMLElementId = pGUICommand.getAttributeAt(1).getHeader();
                    const localHTMLElementAsString = pGUICommand.getAttributeAt(2).getHeader();
                    this.mFrontendWebGUI.setHTMLElementFromString(localHTMLElementId, localHTMLElementAsString);
                    break;
                case CommandProtocol_1.CommandProtocol.SET_CSS:
                    const lCSS = pGUICommand.getOneAttribute().getHeader();
                    this.mFrontendWebGUI.setCSS(lCSS);
                    break;
                case CommandProtocol_1.CommandProtocol.SET_EVENT_FUNCTIONS:
                    const eventFunctions = pGUICommand.getAttributes().to(a => EventFunction_1.EventFunction.fromSpecification(a.toNode()));
                    this.mFrontendWebGUI.setEventFunctions(eventFunctions);
                    break;
                case CommandProtocol_1.CommandProtocol.SET_USER_INPUT_FUNCTIONS:
                    const userInputFunctions = pGUICommand.getAttributes().to(a => UserInputFunction_1.UserInputFunction.fromSpecification(a.toNode()));
                    this.mFrontendWebGUI.setUserInputFunctions(userInputFunctions);
                    break;
                default:
                    throw new Error('The given \'' + pGUICommand + '\' is not valid.');
            }
        }
    }
    exports.FrontendWebClientGUIManager = FrontendWebClientGUIManager;
});
define("System/Application/WebApplicationProtocol/ObjectProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ObjectProtocol {
    }
    ObjectProtocol.CONTROL = 'Control';
    ObjectProtocol.GUI = 'GUI';
    ObjectProtocol.URL = 'URL';
    exports.ObjectProtocol = ObjectProtocol;
});
define("System/Application/WebApplication/ReceiverController", ["require", "exports", "Core/Container/LinkedList/LinkedList"], function (require, exports, LinkedList_10) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ReceiverController {
        constructor(runMethod, getDataMethod) {
            if (runMethod === null) {
                throw new Error('The given run method is null.');
            }
            if (runMethod === undefined) {
                throw new Error('The given run method is undefined.');
            }
            if (getDataMethod === null) {
                throw new Error('The given getData method is null.');
            }
            if (getDataMethod === undefined) {
                throw new Error('The given getData method is undefined.');
            }
            this.runMethod = runMethod;
            this.getDataMethod = getDataMethod;
        }
        getData(request) {
            return this.getDataMethod(request);
        }
        getMultiData(requests) {
            const multiData = new LinkedList_10.LinkedList();
            for (const r of requests) {
                multiData.addAtEnd(this.getDataMethod(r));
            }
            return multiData;
        }
        run(command) {
            this.runMethod(command);
        }
    }
    exports.ReceiverController = ReceiverController;
});
define("System/Application/WebApplicationProtocol/RequestProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class RequestProtocol {
        RequestProtocol() { }
    }
    RequestProtocol.GET_COOKIE_VALUE_BY_COOKIE_NAME = 'GetCookieValueByCookieName';
    RequestProtocol.GET_URL_PARAMETER_VALUE_BY_URL_PARAMETER_NAME = 'GetURLParameterValueByURLParameterName';
    exports.RequestProtocol = RequestProtocol;
});
define("System/Application/WebApplication/TargetApplicationExtractor", ["require", "exports", "Core/Container/SingleContainer/SingleContainer", "Core/Web/URLLineReader"], function (require, exports, SingleContainer_2, URLLineReader_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TargetApplicationExtractor {
        getOptionalTargetApplicationFromURL() {
            const appContainer = URLLineReader_1.URLLineReader.INSTANCE.getOptionalValueOfURLParameterByName('app');
            if (appContainer.containsAny()) {
                const targetApplication = appContainer.getRefElement();
                return SingleContainer_2.SingleContainer.withElement(targetApplication);
            }
            return SingleContainer_2.SingleContainer.withoutElement();
        }
    }
    TargetApplicationExtractor.INSTANCE = new TargetApplicationExtractor();
    exports.TargetApplicationExtractor = TargetApplicationExtractor;
});
define("System/Application/WebApplication/FrontendWebClient", ["require", "exports", "Core/Document/ChainedNode/ChainedNode", "System/Application/WebApplicationProtocol/CommandProtocol", "Core/Web/CookieManager", "System/Application/WebApplication/FrontendWebClientGUIManager", "Core/Container/LinkedList/LinkedList", "Core/Net/EndPoint3/NetEndPoint3", "Core/Document/Node/Node", "System/Application/WebApplicationProtocol/ObjectProtocol", "System/Application/WebApplication/ReceiverController", "System/Application/WebApplicationProtocol/RequestProtocol", "Core/Container/SingleContainer/SingleContainer", "System/Application/WebApplication/TargetApplicationExtractor", "CoreAPI/NetAPI/WebSocketAPI/WebSocketType", "Core/Web/URLLineReader"], function (require, exports, ChainedNode_3, CommandProtocol_2, CookieManager_1, FrontendWebClientGUIManager_1, LinkedList_11, NetEndPoint3_1, Node_6, ObjectProtocol_1, ReceiverController_1, RequestProtocol_1, SingleContainer_3, TargetApplicationExtractor_1, WebSocketType_2, URLLineReader_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontendWebClient {
        static toIpAndPort(ip, port) {
            return new FrontendWebClient(ip, port, SingleContainer_3.SingleContainer.withoutElement(), WebSocketType_2.WebSocketType.BASE_WEB_SOCKET);
        }
        static toIpAndPortOnSecureWebSocket(ip, port) {
            return new FrontendWebClient(ip, port, SingleContainer_3.SingleContainer.withoutElement(), WebSocketType_2.WebSocketType.SECURE_WEB_SOCKET);
        }
        static toIpAndPortAndApplication(ip, port, application) {
            return new FrontendWebClient(ip, port, SingleContainer_3.SingleContainer.withElement(application), WebSocketType_2.WebSocketType.BASE_WEB_SOCKET);
        }
        static toIpAndPortAndApplicationOnSecureWebSocket(ip, port, application) {
            return new FrontendWebClient(ip, port, SingleContainer_3.SingleContainer.withElement(application), WebSocketType_2.WebSocketType.SECURE_WEB_SOCKET);
        }
        static toIpAndPortAndApplicationFromURL(ip, port) {
            return new FrontendWebClient(ip, port, TargetApplicationExtractor_1.TargetApplicationExtractor.INSTANCE.getOptionalTargetApplicationFromURL(), WebSocketType_2.WebSocketType.BASE_WEB_SOCKET);
        }
        static toIpAndPortAndApplicationFromURLOnSecureWebSocket(ip, port) {
            return new FrontendWebClient(ip, port, TargetApplicationExtractor_1.TargetApplicationExtractor.INSTANCE.getOptionalTargetApplicationFromURL(), WebSocketType_2.WebSocketType.SECURE_WEB_SOCKET);
        }
        constructor(ip, port, optionalTarget, webSocketType) {
            this.mGUIManager = FrontendWebClientGUIManager_1.FrontendWebClientGUIManager.withEventTakerAndFileTakerAndWindow((command) => this.takeEvent(command), (controlFixedId, file) => this.takeFile(controlFixedId, file), window);
            this.endPoint = new NetEndPoint3_1.NetEndPoint3(ip, port, optionalTarget, webSocketType);
            this.endPoint.setReceiverController(new ReceiverController_1.ReceiverController(c => this.run(c), r => this.getData(r)));
        }
        createSetFileCommand(controlFixedId, file) {
            return ChainedNode_3.ChainedNode.withHeaderAndNextNode(ObjectProtocol_1.ObjectProtocol.GUI, ChainedNode_3.ChainedNode.withHeaderAndAttributeAndNextNode('ControlByFixedId', ChainedNode_3.ChainedNode.withHeader(controlFixedId), ChainedNode_3.ChainedNode.withHeaderAndAttribute(CommandProtocol_2.CommandProtocol.SET_FILE, ChainedNode_3.ChainedNode.withHeader(file))));
        }
        createSetUserInputsCommand() {
            var userInputs = this.mGUIManager.getUserInputs();
            return ChainedNode_3.ChainedNode.withHeaderAndNextNode(ObjectProtocol_1.ObjectProtocol.GUI, ChainedNode_3.ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol_2.CommandProtocol.SET_USER_INPUTS, userInputs.to(ui => ui.getSpecification())));
        }
        getData(request) {
            switch (request.getHeader()) {
                case RequestProtocol_1.RequestProtocol.GET_COOKIE_VALUE_BY_COOKIE_NAME:
                    const cookieValue = CookieManager_1.CookieManager.INSTANCE.getCookieValueByCookieNameOrEmptyString(request.getOneAttribute().getHeader());
                    if (cookieValue.length === 0) {
                        return new Node_6.Node();
                    }
                    return Node_6.Node.withHeader(cookieValue);
                case RequestProtocol_1.RequestProtocol.GET_URL_PARAMETER_VALUE_BY_URL_PARAMETER_NAME:
                    const conURLParamterName = request.getOneAttribute().getHeader();
                    const conURLParameterValue = URLLineReader_2.URLLineReader.INSTANCE.getOptionalValueOfURLParameterByName(conURLParamterName);
                    if (conURLParameterValue.containsAny()) {
                        return Node_6.Node.withHeader(conURLParameterValue.getRefElement());
                    }
                    return new Node_6.Node();
                default:
                    throw new Error('The given request \'' + request + '\' not valid.');
            }
        }
        openNewTabWithURL(pURL) {
            this.mGUIManager.openNewTabWithURL(pURL);
        }
        run(command) {
            switch (command.getHeader()) {
                case ObjectProtocol_1.ObjectProtocol.GUI:
                    this.mGUIManager.runGUICommand(command.getNextNode());
                    break;
                case CommandProtocol_2.CommandProtocol.OPEN_NEW_TAB:
                    this.runOpenNewTabCommand(command);
                    break;
                case CommandProtocol_2.CommandProtocol.REDIRECT:
                    this.runRedirectCommand(command);
                    break;
                case CommandProtocol_2.CommandProtocol.SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE:
                    this.runSetOrAddCookieWithNameAndValueCommand(command);
                    break;
                case CommandProtocol_2.CommandProtocol.DELETE_COOKIE_BY_NAME:
                    this.runDeleteCookieByNameCommand(command);
                    break;
                case CommandProtocol_2.CommandProtocol.WRITE_TEXT_TO_CLIPBOARD:
                    this.runWriteTextToClipboardCommand(command);
                    break;
                default:
                    throw new Error('The given command \'' + command + '\' is not valid.');
            }
        }
        redirectTo(pURL) {
            this.mGUIManager.redirectTo(pURL);
        }
        runDeleteCookieByNameCommand(deleteCookieByNameCommand) {
            const name = deleteCookieByNameCommand.getOneAttribute().getHeader();
            CookieManager_1.CookieManager.INSTANCE.deleteCookieByName(name);
        }
        runOpenNewTabCommand(openNewTabCommand) {
            const lURL = openNewTabCommand
                .getAttributes()
                .getRefFirstByCondition(a => a.hasGivenHeader(ObjectProtocol_1.ObjectProtocol.URL))
                .getOneAttribute()
                .getHeader();
            this.openNewTabWithURL(lURL);
        }
        runRedirectCommand(redirectCommand) {
            this.redirectTo(redirectCommand.getOneAttribute().getHeader());
        }
        runSetOrAddCookieWithNameAndValueCommand(setOrAddCookieWithNameAndValueCommand) {
            const name = setOrAddCookieWithNameAndValueCommand.getAttributeAt(1).getHeader();
            const value = setOrAddCookieWithNameAndValueCommand.getAttributeAt(2).getHeader();
            CookieManager_1.CookieManager.INSTANCE.setOrAddCookieWithNameAndValue(name, value);
        }
        runWriteTextToClipboardCommand(writeTextToClipboardCommand) {
            const text = writeTextToClipboardCommand.getOneAttribute().getHeader();
            this.writeTextToClipboard(text);
        }
        takeEvent(command) {
            const commands = new LinkedList_11.LinkedList();
            commands.addAtEnd(this.createSetUserInputsCommand());
            commands.addAtEnd(command);
            this.endPoint.runCommands(commands);
        }
        takeFile(controlFixedId, file) {
            const setFileCommand = this.createSetFileCommand(controlFixedId, file);
            this.endPoint.run(setFileCommand);
        }
        writeTextToClipboard(text) {
            window.navigator['clipboard'].writeText(text);
        }
    }
    exports.FrontendWebClient = FrontendWebClient;
});
define("SystemAPI/GUIAPI/StructureProperty/DirectionInRectangle", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var DirectionInRectangle;
    (function (DirectionInRectangle) {
        DirectionInRectangle[DirectionInRectangle["HORIZONTAL"] = 0] = "HORIZONTAL";
        DirectionInRectangle[DirectionInRectangle["VERTICAL"] = 1] = "VERTICAL";
        DirectionInRectangle[DirectionInRectangle["DIAGONAL_UP"] = 2] = "DIAGONAL_UP";
        DirectionInRectangle[DirectionInRectangle["DIAGONAL_DOWN"] = 3] = "DIAGONAL_DOWN";
    })(DirectionInRectangle = exports.DirectionInRectangle || (exports.DirectionInRectangle = {}));
});
define("System/Graphic/Color/ColorGradient", ["require", "exports", "System/Graphic/Color/Color", "SystemAPI/GUIAPI/StructureProperty/DirectionInRectangle"], function (require, exports, Color_2, DirectionInRectangle_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ColorGradient {
        static fromSpecification(specification) {
            return new ColorGradient(DirectionInRectangle_1.DirectionInRectangle[specification.getRefAttributeAtIndex(1).getHeader()], Color_2.Color.fromString(specification.getRefAttributeAtIndex(2).getHeader()), Color_2.Color.fromString(specification.getRefAttributeAtIndex(3).getHeader()));
        }
        static withDirectionAndColor1AndColor2(direction, color1, color2) {
            return new ColorGradient(direction, color1, color2);
        }
        constructor(direction, color1, color2) {
            if (direction === null) {
                throw new Error('The given direction is null.');
            }
            if (direction === undefined) {
                throw new Error('The given direction is undefined.');
            }
            if (color1 === null) {
                throw new Error('The given color1 is null.');
            }
            if (color1 === undefined) {
                throw new Error('The given color1 is undefined.');
            }
            if (color2 === null) {
                throw new Error('The given color2 is null.');
            }
            if (color2 === undefined) {
                throw new Error('The given color2 is undefined.');
            }
            this.direction = direction;
            this.color1 = color1;
            this.color2 = color2;
        }
        getColor1() {
            return this.color1;
        }
        getColor2() {
            return this.color2;
        }
        getDirection() {
            return this.direction;
        }
    }
    exports.ColorGradient = ColorGradient;
});
