define("base/common-type/common-type-constant/string-catalogue", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.StringCatalogue = void 0;
    class StringCatalogue {
        static BINARY_PREFIX = '0b';
        static EMPTY = '';
        static HEXADECIMAL_PREFIX = '0x';
        constructor() { }
    }
    exports.StringCatalogue = StringCatalogue;
});
define("base/common-type/common-type-helper/global-string-helper", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.GlobalStringHelper = void 0;
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
define("base/data-structure/base/container", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Container = void 0;
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
            return this.getSize() === 1;
        }
        forEach(elementTaker) {
            for (const e of this) {
                elementTaker(e);
            }
        }
        getOneAsString() {
            return this.getStoredOne().toString();
        }
        getStoredOne() {
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
            return this.getSize() === 0;
        }
        toString() {
            return this.toStringWithSeparator(',');
        }
        toStringInBrackets() {
            return '(' + this.toString() + ')';
        }
        toStringInBracketsWithSeparator(separator) {
            return '(' + this.toStringWithSeparator(separator) + ')';
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
define("base/data-structure/linked-list/linked-list-node", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.LinkedListNode = void 0;
    class LinkedListNode {
        element;
        nextNode;
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
            return Object.is(this.element, element);
        }
        getStoredElement() {
            return this.element;
        }
        getStoredNextNode() {
            if (this.nextNode === undefined) {
                throw new Error('The current list node does not have a next node.');
            }
            return this.nextNode;
        }
        hasNextNode() {
            return this.nextNode !== undefined;
        }
        hasNextNodeWith(element) {
            return this.nextNode !== undefined && this.nextNode.element === element;
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
define("base/data-structure/linked-list/linked-list-iterator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.LinkedListIterator = void 0;
    class LinkedListIterator {
        currentNode = undefined;
        lastElement = undefined;
        constructor(currentNode) {
            this.currentNode = currentNode;
        }
        next() {
            if (this.currentNode === undefined) {
                return { done: true, value: undefined };
            }
            this.lastElement = this.currentNode.getStoredElement();
            if (!this.currentNode.hasNextNode()) {
                this.currentNode = undefined;
                return { done: false, value: this.lastElement };
            }
            this.currentNode = this.currentNode.getStoredNextNode();
            return { done: false, value: this.lastElement };
        }
        return() {
            return { done: true, value: this.lastElement };
        }
    }
    exports.LinkedListIterator = LinkedListIterator;
});
define("base/data-structure/linked-list/linked-list", ["require", "exports", "base/data-structure/base/container", "base/data-structure/linked-list/linked-list-iterator", "base/data-structure/linked-list/linked-list-node"], function (require, exports, container_1, linked_list_iterator_1, linked_list_node_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.LinkedList = void 0;
    class LinkedList extends container_1.Container {
        static withElement(element) {
            const list = new LinkedList();
            list.addAtEnd(element);
            return list;
        }
        elementCount = 0;
        beginNode = undefined;
        endNode = undefined;
        addAtBegin(element) {
            const newNode = new linked_list_node_1.LinkedListNode(element);
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
            const newNode = new linked_list_node_1.LinkedListNode(element);
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
        getStoredAt(index) {
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
        getStoredFirst() {
            if (this.elementCount === 0) {
                throw new Error("The current List is empty.");
            }
            return this.beginNode.getStoredElement();
        }
        getStoredFirstByCondition(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return e;
                }
            }
            throw new Error("The current List does not contain an element the given selector selects.");
        }
        getStoredFirstByConditionOrNull(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return e;
                }
            }
            return null;
        }
        getStoredFirstOrNull() {
            if (this.elementCount === 0) {
                return null;
            }
            return this.beginNode.getStoredElement();
        }
        getStoredLast() {
            if (this.elementCount === 0) {
                throw new Error("The current List is empty.");
            }
            return this.endNode.getStoredElement();
        }
        getStoredSelected(selector) {
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
                this.beginNode = this.beginNode.getStoredNextNode();
                this.elementCount--;
            }
        }
        removeFirst2(element) {
            if (this.elementCount === 0) {
                return;
            }
            if (element === this.getStoredFirst()) {
                this.removeFirst();
                return;
            }
            var iteratorNode = this.beginNode;
            while (iteratorNode.hasNextNode()) {
                if (iteratorNode.hasNextNodeWith(element)) {
                    this.removeNextNode(iteratorNode);
                    return;
                }
                iteratorNode = iteratorNode.getStoredNextNode();
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
            return new linked_list_iterator_1.LinkedListIterator(this.beginNode);
        }
        removeNextNode(node) {
            const nextNode = node.getStoredNextNode();
            if (!nextNode.hasNextNode()) {
                nextNode.removeNextNode();
                this.endNode = node;
            }
            else {
                node.setNextNode(nextNode.getStoredNextNode());
            }
            this.elementCount--;
            nextNode.removeNextNode();
        }
    }
    exports.LinkedList = LinkedList;
});
define("base/data-structure/matrix/matrix", ["require", "exports", "base/data-structure/base/container", "base/data-structure/matrix/matrix-iterator"], function (require, exports, container_2, matrix_iterator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Matrix = void 0;
    class Matrix extends container_2.Container {
        elements;
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
            return (index - 1) % this.getColumnCount() + 1;
        }
        getCopy() {
            const matrix = new Matrix();
            const rowCount = this.getRowCount();
            for (var i = 0; i < rowCount; i++) {
                matrix.addRow(this.elements[i]);
            }
            return matrix;
        }
        getStoredAt(index) {
            return this.getStoredAtRowAndColumn(this.getRowIndexOf(index), this.getColumnIndexOf(index));
        }
        getStoredAtRowAndColumn(rowIndex, columnIndex) {
            this.assertContainsAtRowAndColumn(rowIndex, columnIndex);
            return this.elements[rowIndex - 1][columnIndex - 1];
        }
        getRowCount() {
            return this.elements.length;
        }
        getRowIndexOf(index) {
            this.assertContainsAt(index);
            return Math.floor((index - 1) / this.getColumnCount()) + 1;
        }
        getSize() {
            if (this.elements.length === 0) {
                return 0;
            }
            return this.elements.length * this.elements[0].length;
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
            return new matrix_iterator_1.MatrixIterator(this);
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
define("base/data-structure/matrix/matrix-iterator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.MatrixIterator = void 0;
    class MatrixIterator {
        parentMatrix;
        currentElementIndex;
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
                value: this.parentMatrix.getStoredAt(this.currentElementIndex - 1)
            };
        }
    }
    exports.MatrixIterator = MatrixIterator;
});
define("base/data-structure/pair/pair", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Pair = void 0;
    class Pair {
        element1;
        element2;
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
        getStoredElement1() {
            return this.element1;
        }
        getStoredElement2() {
            return this.element2;
        }
    }
    exports.Pair = Pair;
});
define("base/data-structure/single-container/single-container", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.SingleContainer = void 0;
    class SingleContainer {
        static EMPTY_CONTAINER = new SingleContainer(undefined);
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
        element;
        constructor(optionalElement) {
            this.element = optionalElement;
        }
        contains(element) {
            return Object.is(this.element, element);
        }
        containsAny() {
            return this.element !== undefined;
        }
        getStoredElement() {
            if (this.element === undefined) {
                throw new Error('The current SingleContainer does not contain an element.');
            }
            return this.element;
        }
        isEmpty() {
            return this.element === undefined;
        }
    }
    exports.SingleContainer = SingleContainer;
});
define("base/document/node/node", ["require", "exports", "base/data-structure/linked-list/linked-list"], function (require, exports, linked_list_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Node = void 0;
    class Node {
        static COMMA_CODE = '$M';
        static DOLLAR_SYMBOL_CODE = '$X';
        static OPEN_BRACKET_CODE = '$O';
        static CLOSED_BRACKET_CODE = '$C';
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
                .replace(',', this.COMMA_CODE)
                .replace('(', this.OPEN_BRACKET_CODE)
                .replace(')', this.CLOSED_BRACKET_CODE);
        }
        static fromNumberPair(numberPair) {
            return new Node()
                .addAttribute(Node.withHeaderFromNumber(numberPair.getStoredElement1()))
                .addAttribute(Node.withHeaderFromNumber(numberPair.getStoredElement2()));
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
        header;
        attributes = new linked_list_1.LinkedList();
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
            return this.getStoredOneAttribute().toNumber();
        }
        getOneAttributeHeader() {
            return this.getStoredOneAttribute().getHeader();
        }
        getStoredAttributeAtIndex(index) {
            return this.attributes.getStoredAt(index);
        }
        getStoredAttributes() {
            return this.attributes;
        }
        getStoredFirstAttributeWithHeader(header) {
            return this.attributes.getStoredFirstByCondition(a => a.hasGivenHeader(header));
        }
        getStoredOneAttribute() {
            return this.attributes.getStoredOne();
        }
        getReproducingHeader() {
            return Node.createReproducingString(this.getHeader());
        }
        hasHeader() {
            return this.header !== undefined;
        }
        hasGivenHeader(header) {
            return this.header !== undefined && this.header === header;
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
                string += '(' + this.getStoredAttributes().toString() + ')';
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
                return index - 1;
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
    exports.Node = Node;
});
define("base/document/chained-node/task", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Task = void 0;
    var Task;
    (function (Task) {
        Task[Task["DO_NOTHING"] = 0] = "DO_NOTHING";
        Task[Task["READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE"] = 1] = "READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE";
        Task[Task["READ_NEXT_NODE"] = 2] = "READ_NEXT_NODE";
    })(Task || (exports.Task = Task = {}));
});
define("base/document/chained-node/chained-node", ["require", "exports", "base/data-structure/linked-list/linked-list", "base/document/node/node", "base/common-type/common-type-constant/string-catalogue", "base/document/chained-node/task"], function (require, exports, linked_list_2, node_1, string_catalogue_1, task_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ChainedNode = void 0;
    class ChainedNode {
        static DOT_CODE = '$D';
        static COMMA_CODE = '$M';
        static DOLLAR_SYMBOL_CODE = '$X';
        static OPEN_BRACKET_CODE = '$O';
        static CLOSED_BRACKET_CODE = '$C';
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
            for (const a of node.getStoredAttributes()) {
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
        header = undefined;
        nextNode = undefined;
        attributes = new linked_list_2.LinkedList();
        containsAttributes() {
            return this.attributes.containsAny();
        }
        getAttributeCount() {
            return this.attributes.getSize();
        }
        getAttributeAt(index) {
            return this.attributes.getStoredAt(index);
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
            return this.attributes.getStoredFirst();
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
                return string_catalogue_1.StringCatalogue.EMPTY;
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
            return this.attributes.getStoredOne();
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
            return this.hasHeader() && this.getHeader() === header;
        }
        hasHeader() {
            return this.header !== undefined;
        }
        hasNextNode() {
            return this.nextNode !== undefined;
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
            const node = new node_1.Node();
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
            return '(' + this.toString() + ')';
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
            var taskAfterSetProbableHeader = task_1.Task.DO_NOTHING;
            var headerLength = 0;
            while (nextIndex < substring.length) {
                var character = substring.charAt(nextIndex);
                if (character == '(') {
                    taskAfterSetProbableHeader = task_1.Task.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE;
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
                    taskAfterSetProbableHeader = task_1.Task.READ_NEXT_NODE;
                    nextIndex++;
                    break;
                }
                nextIndex++;
                headerLength++;
            }
            if (headerLength > 0) {
                this.header = node_1.Node.createOriginStringFromReproducingString(substring.substring(startIndex, startIndex + headerLength));
            }
            var readNextNode = false;
            switch (taskAfterSetProbableHeader) {
                case task_1.Task.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE:
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
                case task_1.Task.DO_NOTHING:
                    return nextIndex;
                case task_1.Task.READ_NEXT_NODE:
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
    exports.ChainedNode = ChainedNode;
});
define("base/math/global-calculator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.GlobalCalculator = void 0;
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
define("base/net/end-point/protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Protocol = void 0;
    class Protocol {
        static TARGET_PREFIX = 'T';
        static MAIN_TARGET_PREFIX = 'A';
        static MESSAGE_PREFIX = 'M';
    }
    exports.Protocol = Protocol;
});
define("base-api/net-api/web-socket-api/web-socket-type", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.WebSocketType = void 0;
    var WebSocketType;
    (function (WebSocketType) {
        WebSocketType[WebSocketType["BASE_WEB_SOCKET"] = 0] = "BASE_WEB_SOCKET";
        WebSocketType[WebSocketType["SECURE_WEB_SOCKET"] = 1] = "SECURE_WEB_SOCKET";
    })(WebSocketType || (exports.WebSocketType = WebSocketType = {}));
});
define("base/net/end-point/net-end-point", ["require", "exports", "base/net/end-point/protocol", "base-api/net-api/web-socket-api/web-socket-type"], function (require, exports, protocol_1, web_socket_type_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.NetEndPoint = void 0;
    class NetEndPoint {
        webSocket;
        target;
        receiver;
        constructor(ip, port, optionalTarget, webSocketType) {
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
                if (optionalTarget.getStoredElement().length === 0) {
                    throw new Error('The given target is empty.');
                }
                this.target = optionalTarget.getStoredElement();
            }
            else {
                this.target = undefined;
            }
            switch (webSocketType) {
                case web_socket_type_1.WebSocketType.BASE_WEB_SOCKET:
                    this.webSocket = new WebSocket('ws://' + ip + ':' + port);
                    break;
                case web_socket_type_1.WebSocketType.SECURE_WEB_SOCKET:
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
            return this.receiver !== undefined;
        }
        hasTarget() {
            return this.target !== undefined;
        }
        send(message) {
            this.sendRawMessage(protocol_1.Protocol.MESSAGE_PREFIX + message);
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
        onMessage = (messageEvent) => {
            this.receive(messageEvent.data);
        };
        onOpen = (event) => {
            console.log("The current NetEndPoint2 has been connected.");
            const targetMessage = (this.target === undefined) ?
                protocol_1.Protocol.MAIN_TARGET_PREFIX :
                protocol_1.Protocol.TARGET_PREFIX + this.target;
            this.sendRawMessage(targetMessage);
        };
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
                case protocol_1.Protocol.MESSAGE_PREFIX:
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
define("base/net/end-point2/message-role", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.MessageRole = void 0;
    var MessageRole;
    (function (MessageRole) {
        MessageRole[MessageRole["TARGET_MESSAGE"] = 0] = "TARGET_MESSAGE";
        MessageRole[MessageRole["STANDARD_MESSAGE"] = 1] = "STANDARD_MESSAGE";
        MessageRole[MessageRole["REPLY_MESSAGE"] = 2] = "REPLY_MESSAGE";
        MessageRole[MessageRole["ERROR_MESSAGE"] = 3] = "ERROR_MESSAGE";
    })(MessageRole || (exports.MessageRole = MessageRole = {}));
});
define("base/net/end-point2/package", ["require", "exports", "base/net/end-point2/message-role"], function (require, exports, message_role_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Package = void 0;
    class Package {
        static TARGET_MESSAGE_PREFIX = 'T';
        static STANDARD_MESSAGE_PREFIX = 'M';
        static REPLY_MESSAGE_PREFIX = 'R';
        static ERROR_MESSAGE_PREFIX = 'E';
        static INDEX_STRING_LENGTH = 8;
        static createFromString(string) {
            return new Package(Number.parseInt(string.substring(0, 8)), Package.createMessageRole(string.substring(8, 9)), string.substring(9, string.length));
        }
        static createMessageRole(messageRolePrefix) {
            switch (messageRolePrefix) {
                case Package.TARGET_MESSAGE_PREFIX:
                    return message_role_1.MessageRole.TARGET_MESSAGE;
                case Package.STANDARD_MESSAGE_PREFIX:
                    return message_role_1.MessageRole.STANDARD_MESSAGE;
                case Package.REPLY_MESSAGE_PREFIX:
                    return message_role_1.MessageRole.REPLY_MESSAGE;
                case Package.ERROR_MESSAGE_PREFIX:
                    return message_role_1.MessageRole.ERROR_MESSAGE;
                default:
                    throw new Error('The given message role prefix is not valid.');
            }
        }
        index;
        messageRole;
        message;
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
            return this.getIndexString() + this.getMessageRolePrefix() + this.message;
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
                case message_role_1.MessageRole.TARGET_MESSAGE:
                    return Package.TARGET_MESSAGE_PREFIX;
                case message_role_1.MessageRole.STANDARD_MESSAGE:
                    return Package.STANDARD_MESSAGE_PREFIX;
                case message_role_1.MessageRole.REPLY_MESSAGE:
                    return Package.REPLY_MESSAGE_PREFIX;
                case message_role_1.MessageRole.ERROR_MESSAGE:
                    return Package.ERROR_MESSAGE_PREFIX;
                default:
                    throw new Error('There is not defined a prefix for the given message role.');
            }
        }
    }
    exports.Package = Package;
});
define("base/net/end-point2/net-end-point2", ["require", "exports", "base/data-structure/linked-list/linked-list", "base/net/end-point2/message-role", "base/net/end-point/net-end-point", "base/net/end-point2/package"], function (require, exports, linked_list_3, message_role_2, net_end_point_1, package_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.NetEndPoint2 = void 0;
    class NetEndPoint2 {
        static TIMEOUT_IN_MILLISECONDS = 5000;
        messageIndex = 0;
        internalNetEndPoint;
        receiver;
        receivedPackages = new linked_list_3.LinkedList();
        constructor(ip, port, optionalTarget, webSocketType) {
            this.internalNetEndPoint = new net_end_point_1.NetEndPoint(ip, port, optionalTarget, webSocketType);
            this.internalNetEndPoint.setReceiver(this.receive);
        }
        getTarget() {
            return this.internalNetEndPoint.getTarget();
        }
        hasReceiver() {
            return this.receiver !== undefined;
        }
        hasTarget() {
            return this.internalNetEndPoint.hasTarget();
        }
        sendAndGetReply(message) {
            const messageIndex = this.getNextMessageIndex();
            this.sendPackage(new package_1.Package(messageIndex, message_role_2.MessageRole.STANDARD_MESSAGE, message));
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
        receive = (message) => {
            this.receivePackage(package_1.Package.createFromString(message));
        };
        receivePackage(package_) {
            switch (package_.getMessageRole()) {
                case message_role_2.MessageRole.STANDARD_MESSAGE:
                    if (this.receiver === undefined) {
                        throw new Error('The current NetEndPoint3 does not have a receiver.');
                    }
                    try {
                        this.sendPackage(new package_1.Package(package_.getIndex(), message_role_2.MessageRole.REPLY_MESSAGE, this.receiver(package_.getMessage())));
                    }
                    catch (error) {
                        this.sendPackage(new package_1.Package(package_.getIndex(), message_role_2.MessageRole.ERROR_MESSAGE, 'ERROR_MESSAGE'));
                    }
                    break;
                case message_role_2.MessageRole.REPLY_MESSAGE:
                case message_role_2.MessageRole.ERROR_MESSAGE:
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
                const package_ = this.receivedPackages.getStoredFirstByConditionOrNull(rp => rp.hasIndex(index));
                if (package_ !== null) {
                    this.receivedPackages.removeFirst2(package_);
                    return package_;
                }
            }
            throw new Error('The current NetEndPoint3 reached the timeout by waiting to a message.');
        }
    }
    exports.NetEndPoint2 = NetEndPoint2;
});
define("base/net/end-point3/i-data-provider-controller", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("base/net/end-point3/protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Protocol = void 0;
    class Protocol {
        static COMMANDS_HEADER = 'Commands';
        static DONE_HEADER = 'Done';
        static ERROR_HEADER = 'Error';
        static MULTI_DATA_HEADER = 'MultiData';
        static MULTI_DATA_REQUEST_HEADER = 'MultiDataRequest';
    }
    exports.Protocol = Protocol;
});
define("base/net/end-point3/net-end-point3", ["require", "exports", "base/document/chained-node/chained-node", "base/data-structure/linked-list/linked-list", "base/net/end-point2/net-end-point2", "base/document/node/node", "base/net/end-point3/protocol"], function (require, exports, chained_node_1, linked_list_4, net_end_point2_1, node_2, protocol_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.NetEndPoint3 = void 0;
    class NetEndPoint3 {
        internalNetEndPoint;
        receiverController;
        constructor(ip, port, optionalTarget, webSocketType) {
            this.internalNetEndPoint = new net_end_point2_1.NetEndPoint2(ip, port, optionalTarget, webSocketType);
            this.internalNetEndPoint.setReceiver(this.receiveMessageAndGetReply);
        }
        getData(request) {
            const requests = linked_list_4.LinkedList.withElement(request);
            return this.getMultiData(requests).getStoredOne();
        }
        getMultiData(requests) {
            const message = protocol_2.Protocol.MULTI_DATA_REQUEST_HEADER + requests.toStringInBrackets();
            const reply = node_2.Node.fromString(this.internalNetEndPoint.sendAndGetReply(message));
            switch (reply.getHeader()) {
                case protocol_2.Protocol.MULTI_DATA_HEADER:
                    return reply.getStoredAttributes();
                case protocol_2.Protocol.ERROR_HEADER:
                    throw new Error(reply.getOneAttributeHeader());
                default:
                    throw new Error('The given reply is not valid.');
            }
        }
        getTarget() {
            return this.internalNetEndPoint.getTarget();
        }
        hasReceiverController() {
            return this.receiverController !== undefined;
        }
        run(command) {
            const commands = new linked_list_4.LinkedList();
            commands.addAtEnd(command);
            this.runCommands(commands);
        }
        runCommands(commands) {
            const message = protocol_2.Protocol.COMMANDS_HEADER + commands.toStringInBrackets();
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
        receiveMessageAndGetReply = (message) => {
            try {
                return this.receiveDocumentNodeMessageAndGetReply(chained_node_1.ChainedNode.fromString(message));
            }
            catch (error) {
                if (error === undefined || error == null) {
                    return protocol_2.Protocol.ERROR_HEADER + node_2.Node.withHeader('An error occured.');
                }
                if (error instanceof Error) {
                    return protocol_2.Protocol.ERROR_HEADER + node_2.Node.withHeader(error.message);
                }
                return protocol_2.Protocol.ERROR_HEADER + node_2.Node.withHeader('An error occured: ' + error);
            }
        };
        receiveDocumentNodeMessageAndGetReply(message) {
            const receiverController = this.getReceiverController();
            switch (message.getHeader()) {
                case protocol_2.Protocol.COMMANDS_HEADER:
                    for (const a of message.getAttributes()) {
                        receiverController.run(a);
                    }
                    return protocol_2.Protocol.DONE_HEADER;
                case protocol_2.Protocol.MULTI_DATA_REQUEST_HEADER:
                    return protocol_2.Protocol.MULTI_DATA_HEADER + '(' + receiverController.getMultiData(message.getAttributes()) + ')';
                default:
                    throw new Error('The given message is not valid.');
            }
        }
    }
    exports.NetEndPoint3 = NetEndPoint3;
});
define("base/program-atom/name/pascal-case-catalogue", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.PascalCaseCatalogue = void 0;
    class PascalCaseCatalogue {
        static CURSOR_POSITION = 'CursorPosition';
        static HEIGHT = 'Height';
        static SIZE = 'Size';
        static WIDTH = 'Width';
        constructor() { }
    }
    exports.PascalCaseCatalogue = PascalCaseCatalogue;
});
define("base/program-structure/caching/caching-container", ["require", "exports", "base/data-structure/linked-list/linked-list", "base/data-structure/pair/pair"], function (require, exports, linked_list_5, pair_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.CachingContainer = void 0;
    class CachingContainer {
        elements = new linked_list_5.LinkedList();
        contains(element) {
            return this.elements.contains(e => Object.is(e, element));
        }
        containsWithId(id) {
            return this.elements.contains(e => e.getStoredElement1() === id);
        }
        getStoredById(id) {
            return this.elements.getStoredFirstByCondition(e => e.getStoredElement1() === id).getStoredElement2();
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
            this.elements.addAtEnd(new pair_1.Pair(id, element));
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
define("base/testing/base-test/base-test", ["require", "exports", "base/data-structure/linked-list/linked-list"], function (require, exports, linked_list_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.BaseTest = void 0;
    class BaseTest {
        currentTestCaseErrors = new linked_list_6.LinkedList();
        run() {
            console.log('Start ' + this.constructor.name);
            for (const tc of this.getStoredTestCases()) {
                this.runTestCase(tc);
            }
            console.log();
        }
        addErrorForCurrentTestCase(error) {
            this.currentTestCaseErrors.addAtEnd(error);
        }
        getStoredProperties() {
            var properties = [];
            var prototype = this;
            do {
                properties = properties.concat(Object.getOwnPropertyNames(prototype));
            } while (prototype = Object.getPrototypeOf(prototype));
            return properties;
        }
        getStoredTestCases() {
            var testCases = [];
            for (const p of this.getStoredProperties()) {
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
define("base/testing/test/number-mediator", ["require", "exports", "base/testing/test/mediator"], function (require, exports, mediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.NumberMediator = void 0;
    class NumberMediator extends mediator_1.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
    }
    exports.NumberMediator = NumberMediator;
});
define("base/testing/test/string-mediator", ["require", "exports", "base/testing/test/mediator"], function (require, exports, mediator_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.StringMediator = void 0;
    class StringMediator extends mediator_2.Mediator {
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
define("base/testing/test/test", ["require", "exports", "base/testing/base-test/base-test", "base/testing/test/function-mediator", "base/testing/test/mediator", "base/testing/test/number-mediator", "base/testing/test/string-mediator"], function (require, exports, base_test_1, function_mediator_1, mediator_3, number_mediator_1, string_mediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Test = void 0;
    class Test extends base_test_1.BaseTest {
        expect(argument) {
            if (!argument) {
                this.addErrorForCurrentTestCase('True was expected, but false was received.');
            }
        }
        expectFunction(argument) {
            return new function_mediator_1.FunctionMediator(this, argument);
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
            return new number_mediator_1.NumberMediator(this, argument);
        }
        expectObject(argument) {
            return new mediator_3.Mediator(this, argument);
        }
        expectString(argument) {
            return new string_mediator_1.StringMediator(this, argument);
        }
    }
    exports.Test = Test;
});
define("base/testing/test/mediator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Mediator = void 0;
    class Mediator {
        test;
        argument;
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
define("base/testing/test/thrown-error-mediator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ThrownErrorMediator = void 0;
    class ThrownErrorMediator {
        test;
        error;
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
define("base/testing/test/function-mediator", ["require", "exports", "base/testing/test/mediator", "base/testing/test/thrown-error-mediator"], function (require, exports, mediator_4, thrown_error_mediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.FunctionMediator = void 0;
    class FunctionMediator extends mediator_4.Mediator {
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
                return new thrown_error_mediator_1.ThrownErrorMediator(this.test, error);
            }
        }
    }
    exports.FunctionMediator = FunctionMediator;
});
define("base/testing/test/test-pool", ["require", "exports", "base/data-structure/linked-list/linked-list"], function (require, exports, linked_list_7) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.TestPool = void 0;
    class TestPool {
        tests = new linked_list_7.LinkedList();
        testPools = new linked_list_7.LinkedList();
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
define("base/web/cookie-manager", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.CookieManager = void 0;
    class CookieManager {
        static COOKIE_MAX_AGE_IN_SECONDS = 31536000;
        static INSTANCE = new CookieManager();
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
    exports.CookieManager = CookieManager;
});
define("base/web/url-line-reader", ["require", "exports", "base/data-structure/single-container/single-container"], function (require, exports, single_container_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.URLLineReader = void 0;
    class URLLineReader {
        static INSTANCE = new URLLineReader();
        constructor() { }
        getOptionalValueOfURLParameterByName(name) {
            const parameterString = window.location.search;
            const localURLSearchParams = new URLSearchParams(parameterString);
            const value = localURLSearchParams.get(name);
            if (value === null) {
                return single_container_1.SingleContainer.withoutElement();
            }
            return single_container_1.SingleContainer.withElement(value);
        }
    }
    exports.URLLineReader = URLLineReader;
});
define("base-test/data-structure-test/linked-list-test/linked-list-test", ["require", "exports", "base/data-structure/linked-list/linked-list", "base/testing/test/test"], function (require, exports, linked_list_8, test_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.LinkedListTest = void 0;
    class LinkedListTest extends test_1.Test {
        testCase_addAtBegin_WhenNullIsGiven() {
            const list = new linked_list_8.LinkedList();
            this.expectFunction(() => list.addAtBegin(null)).throwsError();
        }
        testCase_addAtEnd() {
            const list = new linked_list_8.LinkedList();
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            this.expectNumber(list.getSize()).equals(3);
            this.expectString(list.getStoredAt(1)).isSameAs(apple);
            this.expectString(list.getStoredAt(2)).isSameAs(banana);
            this.expectString(list.getStoredAt(3)).isSameAs(cerish);
        }
        testCase_addAtEnd_WhenNullIsGiven() {
            const list = new linked_list_8.LinkedList();
            this.expectFunction(() => list.addAtEnd(null)).throwsError();
        }
        testCase_clear() {
            const list = new linked_list_8.LinkedList();
            list.addAtEnd('apple');
            list.addAtEnd('banana');
            list.addAtEnd('cerish');
            this.expectNumber(list.getSize()).equals(3);
            list.clear();
            this.expect(list.isEmpty());
        }
        testCase_constructor() {
            const list = new linked_list_8.LinkedList();
            this.expect(list.isEmpty());
        }
        testCase_contains() {
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            const lemon = 'lemon';
            const list = new linked_list_8.LinkedList();
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            this.expectNumber(list.getSize()).equals(3);
            this.expect(list.containsGivenElement(apple));
            this.expect(list.containsGivenElement(banana));
            this.expect(list.containsGivenElement(cerish));
            this.expectNot(list.containsGivenElement(lemon));
        }
        testCase_iterator_whenListContains3Elements() {
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            const lemon = 'lemon';
            const list = new linked_list_8.LinkedList();
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            var i = 1;
            for (const e of list) {
                switch (i) {
                    case 1:
                        this.expectString(e).isSameAs(apple);
                        break;
                    case 2:
                        this.expectString(e).isSameAs(banana);
                        break;
                    case 3:
                        this.expectString(e).isSameAs(cerish);
                        break;
                    default:
                        this.expectNotThisCase();
                }
                i++;
            }
        }
        testCase_iterator_whenListIsEmpty() {
            const list = new linked_list_8.LinkedList();
            var foundElement = false;
            for (const e of list) {
                foundElement = true;
            }
            this.expectNot(foundElement);
        }
        testCase_removeFirst() {
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            const list = new linked_list_8.LinkedList();
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            this.expectNumber(list.getSize()).equals(3);
            this.expectString(list.getStoredFirst()).isSameAs(apple);
            list.removeFirst();
            this.expectNumber(list.getSize()).equals(2);
            this.expectString(list.getStoredFirst()).isSameAs(banana);
        }
    }
    exports.LinkedListTest = LinkedListTest;
});
define("base-test/data-structure-test/matrix-test/matrix-test", ["require", "exports", "base/data-structure/matrix/matrix", "base/testing/test/test"], function (require, exports, matrix_1, test_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.MatrixTest = void 0;
    class MatrixTest extends test_2.Test {
        testCase_addColumn() {
            const testUnit = new matrix_1.Matrix();
            testUnit.addColumn(['apple', 'banana', 'cerish']);
            this.expectNumber(testUnit.getRowCount()).equals(3);
            this.expectNumber(testUnit.getColumnCount()).equals(1);
            this.expectString(testUnit.getStoredAtRowAndColumn(1, 1)).equals('apple');
            this.expectString(testUnit.getStoredAtRowAndColumn(2, 1)).equals('banana');
            this.expectString(testUnit.getStoredAtRowAndColumn(3, 1)).equals('cerish');
        }
        testCase_addRow() {
            const testUnit = new matrix_1.Matrix();
            testUnit.addRow(["apple", "banana", "cerish"]);
            this.expectNumber(testUnit.getRowCount()).equals(1);
            this.expectNumber(testUnit.getColumnCount()).equals(3);
            this.expectString(testUnit.getStoredAtRowAndColumn(1, 1)).equals('apple');
            this.expectString(testUnit.getStoredAtRowAndColumn(1, 2)).equals('banana');
            this.expectString(testUnit.getStoredAtRowAndColumn(1, 3)).equals('cerish');
        }
        testCase_creation() {
            const testUnit = new matrix_1.Matrix();
            this.expectNumber(testUnit.getRowCount()).equals(0);
            this.expectNumber(testUnit.getColumnCount()).equals(0);
        }
    }
    exports.MatrixTest = MatrixTest;
});
define("base-test/data-structure-test/matrix-test/matrix-test-pool", ["require", "exports", "base-test/data-structure-test/matrix-test/matrix-test", "base/testing/test/test-pool"], function (require, exports, matrix_test_1, test_pool_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.MatrixTestPool = void 0;
    class MatrixTestPool extends test_pool_1.TestPool {
        constructor() {
            super();
            this.addTest(new matrix_test_1.MatrixTest());
        }
    }
    exports.MatrixTestPool = MatrixTestPool;
});
define("base-test/data-structure-test/single-container-test/single-container-test", ["require", "exports", "base/data-structure/single-container/single-container", "base/testing/test/test"], function (require, exports, single_container_2, test_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.SingleContainerTest = void 0;
    class SingleContainerTest extends test_3.Test {
        testCase_createWith_whenTheGivenElementIsNull() {
            this.expectFunction(() => single_container_2.SingleContainer.withElement(null))
                .throwsError()
                .withMessage('The given element is null.');
        }
        testCase_createWith_whenTheGivenElementIsUndefined() {
            this.expectFunction(() => single_container_2.SingleContainer.withElement(undefined))
                .throwsError()
                .withMessage('The given element is undefined.');
        }
    }
    exports.SingleContainerTest = SingleContainerTest;
});
define("base-test/data-structure-test/linked-list-test/linked-list-test-pool", ["require", "exports", "base-test/data-structure-test/linked-list-test/linked-list-test", "base/testing/test/test-pool"], function (require, exports, linked_list_test_1, test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.LinkedListTestPool = void 0;
    class LinkedListTestPool extends test_pool_2.TestPool {
        constructor() {
            super();
            this.addTest(new linked_list_test_1.LinkedListTest());
        }
    }
    exports.LinkedListTestPool = LinkedListTestPool;
});
define("base-test/data-structure-test/single-container-test/launcher", ["require", "exports", "base-test/data-structure-test/single-container-test/single-container-test", "base/testing/test/test-pool"], function (require, exports, single_container_test_1, test_pool_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.SingleContainerTestPool = void 0;
    class SingleContainerTestPool extends test_pool_3.TestPool {
        constructor() {
            super();
            this.addTest(new single_container_test_1.SingleContainerTest());
        }
    }
    exports.SingleContainerTestPool = SingleContainerTestPool;
});
define("base-test/data-structure-test/container-test-pool", ["require", "exports", "base-test/data-structure-test/matrix-test/matrix-test-pool", "base/testing/test/test-pool", "base-test/data-structure-test/linked-list-test/linked-list-test-pool", "base-test/data-structure-test/single-container-test/launcher"], function (require, exports, matrix_test_pool_1, test_pool_4, linked_list_test_pool_1, launcher_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ContainerTestPool = void 0;
    class ContainerTestPool extends test_pool_4.TestPool {
        constructor() {
            super();
            this.addTestPool(new linked_list_test_pool_1.LinkedListTestPool());
            this.addTestPool(new matrix_test_pool_1.MatrixTestPool());
            this.addTestPool(new launcher_1.SingleContainerTestPool());
        }
    }
    exports.ContainerTestPool = ContainerTestPool;
});
define("base-test/document-test/chained-node-test/chained-node-test", ["require", "exports", "base/document/chained-node/chained-node", "base/testing/test/test"], function (require, exports, chained_node_2, test_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ChainedNodeTest = void 0;
    class ChainedNodeTest extends test_4.Test {
        testCase_fromString_1A() {
            const chainedNode = chained_node_2.ChainedNode.fromString("");
            this.expectNot(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("");
        }
        testCase_fromString_1B() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a");
        }
        testCase_fromString_1C() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b)");
        }
        testCase_fromString_2A() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a.b");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.b");
        }
        testCase_fromString_2B() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b).c");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b).c");
        }
        testCase_fromString_2C() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a.b(c)");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.b(c)");
        }
        testCase_fromString_2D() {
            const chainedNode = chained_node_2.ChainedNode.fromString("(a.b).c");
            this.expectNot(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("(a.b).c");
        }
        testCase_fromString_2E() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a.(b.c)");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.(b.c)");
        }
        testCase_fromString_2F() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a.b.c");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.b.c");
        }
        testCase_fromString_3A() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b,c,d)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b,c,d)");
        }
        testCase_fromString_3B() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b(c),d(e),f(g))");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b(c),d(e),f(g))");
        }
        testCase_fromString_3C() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b.c,d.e,f.g)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b.c,d.e,f.g)");
        }
        testCase_fromString_3D() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b(c).d,e(f).g,h(i).j)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b(c).d,e(f).g,h(i).j)");
        }
        testCase_fromString_3E() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b.c(d),e.(f.g),h.(i,j))");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b.c(d),e.(f.g),h.(i,j))");
        }
        testCase_fromString_3F() {
            const chainedNode = chained_node_2.ChainedNode.fromString("a(b.c.d,e.f.g,h.i.j)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b.c.d,e.f.g,h.i.j)");
        }
    }
    exports.ChainedNodeTest = ChainedNodeTest;
});
define("base-test/document-test/chained-node-test/chained-node-test-pool", ["require", "exports", "base-test/document-test/chained-node-test/chained-node-test", "base/testing/test/test-pool"], function (require, exports, chained_node_test_1, test_pool_5) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ChainedNodeTestPool = void 0;
    class ChainedNodeTestPool extends test_pool_5.TestPool {
        constructor() {
            super();
            this.addTest(new chained_node_test_1.ChainedNodeTest());
        }
    }
    exports.ChainedNodeTestPool = ChainedNodeTestPool;
});
define("base-test/document-test/node-test/node-test", ["require", "exports", "base/document/node/node", "base/testing/test/test"], function (require, exports, node_3, test_5) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.NodeTest = void 0;
    class NodeTest extends test_5.Test {
        testCase_addAttribute() {
            const node = new node_3.Node();
            const attribute1 = new node_3.Node();
            const attribute2 = new node_3.Node();
            const attribtue3 = new node_3.Node();
            this.expectNot(node.containsAttributes());
            node.addAttribute(attribute1);
            node.addAttribute(attribute2);
            node.addAttribute(attribtue3);
            this.expectNumber(node.getAttributeCount()).equals(3);
        }
        testCase_resetFrom() {
            const node = new node_3.Node();
            node.resetFrom('');
            this.expectString(node.toString()).equals('');
        }
        testCase_resetFrom2() {
            const node = new node_3.Node();
            node.resetFrom('A');
            this.expectString(node.toString()).equals('A');
        }
        testCase_resetFrom3() {
            const node = new node_3.Node();
            node.resetFrom('A(B)');
            this.expectString(node.toString()).equals('A(B)');
        }
        testCase_resetFrom4() {
            const node = new node_3.Node();
            node.resetFrom('A(B1,B2,B3)');
            this.expectString(node.toString()).equals('A(B1,B2,B3)');
        }
        testCase_resetFrom5() {
            const node = new node_3.Node();
            node.resetFrom('A(B(C))');
            this.expectString(node.toString()).equals('A(B(C))');
        }
        testCase_resetFrom6() {
            const node = new node_3.Node();
            node.resetFrom('A(B1(C1),B2(C2))');
            this.expectString(node.toString()).equals('A(B1(C1),B2(C2))');
        }
        testCase_resetFrom7() {
            const node = new node_3.Node();
            node.resetFrom('A(B1(C1,C2),B2(C3,C4))');
            this.expectString(node.toString()).equals('A(B1(C1,C2),B2(C3,C4))');
        }
        testCase_setHeader() {
            const node = new node_3.Node();
            this.expectNot(node.hasHeader());
            node.setHeader('H');
            this.expectString(node.getHeader()).equals('H');
        }
    }
    exports.NodeTest = NodeTest;
});
define("base-test/document-test/node-test/node-test-pool", ["require", "exports", "base-test/document-test/node-test/node-test", "base/testing/test/test-pool"], function (require, exports, node_test_1, test_pool_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.NodeTestPool = void 0;
    class NodeTestPool extends test_pool_6.TestPool {
        constructor() {
            super();
            this.addTest(new node_test_1.NodeTest());
        }
    }
    exports.NodeTestPool = NodeTestPool;
});
define("base-test/document-test/document-test-pool", ["require", "exports", "base-test/document-test/chained-node-test/chained-node-test-pool", "base-test/document-test/node-test/node-test-pool", "base/testing/test/test-pool"], function (require, exports, chained_node_test_pool_1, node_test_pool_1, test_pool_7) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.DocumentTestPool = void 0;
    class DocumentTestPool extends test_pool_7.TestPool {
        constructor() {
            super();
            this.addTestPool(new chained_node_test_pool_1.ChainedNodeTestPool);
            this.addTestPool(new node_test_pool_1.NodeTestPool());
        }
    }
    exports.DocumentTestPool = DocumentTestPool;
});
define("base-test/core-test-pool", ["require", "exports", "base-test/data-structure-test/container-test-pool", "base-test/document-test/document-test-pool", "base/testing/test/test-pool"], function (require, exports, container_test_pool_1, document_test_pool_1, test_pool_8) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.CoreTestPool = void 0;
    class CoreTestPool extends test_pool_8.TestPool {
        constructor() {
            super();
            this.addTestPool(new container_test_pool_1.ContainerTestPool());
            this.addTestPool(new document_test_pool_1.DocumentTestPool());
        }
    }
    exports.CoreTestPool = CoreTestPool;
});
define("base-test/launcher", ["require", "exports", "base-test/core-test-pool"], function (require, exports, core_test_pool_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new core_test_pool_1.CoreTestPool().run();
});
define("base-test/data-structure-test/launcher", ["require", "exports", "base-test/data-structure-test/container-test-pool"], function (require, exports, container_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new container_test_pool_2.ContainerTestPool().run();
});
define("base-test/data-structure-test/linked-list-test/launcher", ["require", "exports", "base-test/data-structure-test/linked-list-test/linked-list-test-pool"], function (require, exports, linked_list_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new linked_list_test_pool_2.LinkedListTestPool().run();
});
define("base-test/data-structure-test/matrix-test/launcher", ["require", "exports", "base-test/data-structure-test/matrix-test/matrix-test-pool"], function (require, exports, matrix_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new matrix_test_pool_2.MatrixTestPool().run();
});
define("base-test/document-test/launcher", ["require", "exports", "base-test/document-test/document-test-pool"], function (require, exports, document_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new document_test_pool_2.DocumentTestPool().run();
});
define("base-test/document-test/chained-node-test/launcher", ["require", "exports", "base-test/document-test/chained-node-test/chained-node-test-pool"], function (require, exports, chained_node_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new chained_node_test_pool_2.ChainedNodeTestPool().run();
});
define("base-test/document-test/node-test/launcher", ["require", "exports", "base-test/document-test/node-test/node-test-pool"], function (require, exports, node_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new node_test_pool_2.NodeTestPool().run();
});
define("system/application/web-application-protocol/command-protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.CommandProtocol = void 0;
    class CommandProtocol {
        static DELETE_COOKIE_BY_NAME = "DeleteCookieByName";
        static OPEN_NEW_TAB = 'OpenNewTab';
        static REDIRECT = "Redirect";
        static SET_CSS = "SetCSS";
        static SET_EVENT_FUNCTIONS = 'SetEventFunctions';
        static SET_FILE = 'SetFile';
        static SET_HTML_ELEMENT = "SetHTMLElement";
        static SET_ICON = 'SetIcon';
        static SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE = 'SetOrAddCookieWithNameAndValue';
        static SET_ROOT_HTML_ELEMENT = 'SetRootHTMLElement';
        static SET_TITLE = 'SetTitle';
        static SET_USER_INPUT_FUNCTIONS = 'SetUserInputFunctions';
        static SET_USER_INPUTS = 'SetUserInputs';
        static WRITE_TEXT_TO_CLIPBOARD = 'WriteTextToClipBoard';
    }
    exports.CommandProtocol = CommandProtocol;
});
define("system/frontend-web-gui/event-function", ["require", "exports", "base/document/chained-node/chained-node"], function (require, exports, chained_node_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.EventFunction = void 0;
    class EventFunction {
        static fromSpecification(specification) {
            const lHTMLElementId = specification.getStoredAttributeAtIndex(1).getHeader();
            const lHTMLEventName = specification.getStoredAttributeAtIndex(2).getHeader();
            return this.withHTMLElementIdAndHtmlEventName(lHTMLElementId, lHTMLEventName);
        }
        static withHTMLElementIdAndHtmlEventName(pHTMLElementId, pHTMLEventName) {
            return new EventFunction(pHTMLElementId, pHTMLEventName);
        }
        mHTMLElementId;
        mHTMLEventName;
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
            return chained_node_3.ChainedNode.fromString('GUI.ControlByFixedId(' + this.getHTMLElementId() + ').RunHTMLEvent(' + this.getHTMLEventName() + ')');
        }
    }
    exports.EventFunction = EventFunction;
});
define("system-api/graphic-api/image-api/i-image", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("system-api/frontend-web-guiapi/i-frontend-web-gui", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("system/element/element", ["require", "exports", "base/document/node/node"], function (require, exports, node_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Element = void 0;
    class Element {
        getSpecification() {
            return this.getSpecificationAs(this.getType());
        }
        getSpecificationAs(type) {
            return node_4.Node.withHeaderAndAttributes(type, this.getAttributes());
        }
        toString() {
            return this.getSpecification().toString();
        }
    }
    exports.Element = Element;
});
define("system/frontend-web-gui/user-input", ["require", "exports", "system/element/element", "base/data-structure/linked-list/linked-list", "base/document/node/node"], function (require, exports, element_1, linked_list_9, node_5) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.UserInput = void 0;
    class UserInput extends element_1.Element {
        static TYPE_HEADER = 'UserInput';
        static withHTMLElementIdAndUserInput(pHTMLElementId, userInput) {
            return new UserInput(pHTMLElementId, userInput);
        }
        mHTMLElementId;
        userInput;
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
        getAttributes() {
            const attributes = new linked_list_9.LinkedList();
            attributes.addAtEnd(node_5.Node.withHeader(this.getHTMLElementId()));
            const userInput = this.getUserInput();
            if (userInput.length === 0) {
                attributes.addAtEnd(new node_5.Node());
            }
            else {
                attributes.addAtEnd(node_5.Node.withHeader(userInput));
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
    exports.UserInput = UserInput;
});
define("system/frontend-web-gui/user-input-function", ["require", "exports", "system/frontend-web-gui/user-input"], function (require, exports, user_input_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.UserInputFunction = void 0;
    class UserInputFunction {
        static fromSpecification(specification) {
            const lHTMLElementId = specification.getStoredAttributeAtIndex(1).getHeader();
            const userInputFunctionString = specification.getStoredAttributeAtIndex(2).getHeader();
            return this.withHTMLElementIdAndUserInputFunctionFromString(lHTMLElementId, userInputFunctionString);
        }
        static withHTMLElementIdAndUserInputFunctionFromString(pHTMLElementId, userInputFunctionString) {
            return new UserInputFunction(pHTMLElementId, Function('x', userInputFunctionString));
        }
        mHTMLElementId;
        userInputFunction;
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
            return user_input_1.UserInput.withHTMLElementIdAndUserInput(this.getHTMLElementId(), userInput);
        }
    }
    exports.UserInputFunction = UserInputFunction;
});
define("system/frontend-web-gui/frontend-web-gui", ["require", "exports", "base/data-structure/linked-list/linked-list"], function (require, exports, linked_list_10) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.FrontendWebGUI = void 0;
    class FrontendWebGUI {
        static withEventTakerAndFileTakerAndWindow(eventTaker, fileTaker, window) {
            return new FrontendWebGUI(eventTaker, fileTaker, window);
        }
        title;
        icon;
        eventTaker;
        fileTaker;
        window;
        style;
        userInputFunctions = new linked_list_10.LinkedList();
        constructor(eventTaker, fileTaker, window) {
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
        getIcon() {
            return this.icon;
        }
        getTitle() {
            return this.title;
        }
        getUserInputs() {
            const userInputs = new linked_list_10.LinkedList();
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
            if (localHTMLElement.id !== paramHTMLElementId) {
                throw new Error('The given HtmlElementAsString is not valid.');
            }
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
            const rootElement = this.getStoredRootElement();
            rootElement.outerHTML = rootHTMLElementAsString;
            if (rootElement.id !== 'root') {
                throw new Error('The given rootHtmlElementAsString is not valid.');
            }
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
        getStoredRootElement() {
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
define("system/graphic/color/color", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Color = void 0;
    class Color {
        static BLACK = new Color(0, 0, 0, 255);
        static WHITE = new Color(255, 255, 255, 255);
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
        redValue;
        greenValue;
        blueValue;
        alphaValue;
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
    exports.Color = Color;
});
define("system/graphic/image/image", ["require", "exports", "system/graphic/color/color", "system/element/element", "base/data-structure/matrix/matrix", "base/document/node/node", "base/program-atom/name/pascal-case-catalogue"], function (require, exports, color_1, element_2, matrix_2, node_6, pascal_case_catalogue_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.Image = void 0;
    class Image extends element_2.Element {
        static TYPE_HEADER = 'Image';
        static PIXEL_ARRAY_HEADER = 'PixelArray';
        static WIDTH_HEADER = 'Width';
        static PIXELS_HEADER = 'Pixels';
        static fromSpecification(specification) {
            const pixelArraySpecification = specification.getStoredFirstAttributeWithHeader(Image.PIXEL_ARRAY_HEADER);
            const widthSpecification = pixelArraySpecification.getStoredFirstAttributeWithHeader(pascal_case_catalogue_1.PascalCaseCatalogue.WIDTH);
            const width = widthSpecification.getOneAttributeAsNumber();
            const pixelsSpecification = pixelArraySpecification.getStoredFirstAttributeWithHeader(Image.PIXELS_HEADER);
            const pixels = new matrix_2.Matrix();
            var row = new Array();
            var index = 1;
            for (const p of pixelsSpecification.getStoredAttributes()) {
                const pixel = color_1.Color.fromSpecification(node_6.Node.withAttribute(p));
                row.push(pixel);
                index++;
                if (index > width) {
                    pixels.addRow(row);
                    row = new Array();
                    index = 1;
                }
            }
            return new Image(pixels);
        }
        pixels;
        canvas;
        constructor(pixels) {
            super();
            this.pixels = pixels;
        }
        getAttributes() {
            throw new Error("Method not implemented.");
        }
        getHeight() {
            return this.pixels.getRowCount();
        }
        getPixelAtIndex(index) {
            return this.pixels.getStoredAt(index);
        }
        getPixelAtPosition(xPosition, yPosition) {
            return this.pixels.getStoredAtRowAndColumn(xPosition, yPosition);
        }
        getSizeInPixel() {
            return this.getWidth() * this.getHeight();
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
            return this.canvas === undefined;
        }
    }
    exports.Image = Image;
});
define("system/application/web-application/frontend-web-client-gui-manager", ["require", "exports", "system/application/web-application-protocol/command-protocol", "system/frontend-web-gui/frontend-web-gui", "system/graphic/image/image", "system/frontend-web-gui/user-input-function", "system/frontend-web-gui/event-function"], function (require, exports, command_protocol_1, frontend_web_gui_1, image_1, user_input_function_1, event_function_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.FrontendWebClientGUIManager = void 0;
    class FrontendWebClientGUIManager {
        static withEventTakerAndFileTakerAndWindow(eventTaker, fileTaker, window) {
            return new FrontendWebClientGUIManager(eventTaker, fileTaker, window);
        }
        mFrontendWebGUI;
        constructor(eventTaker, fileTaker, window) {
            this.mFrontendWebGUI = frontend_web_gui_1.FrontendWebGUI.withEventTakerAndFileTakerAndWindow(eventTaker, fileTaker, window);
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
                case command_protocol_1.CommandProtocol.SET_TITLE:
                    this.mFrontendWebGUI.setTitle(pGUICommand.getOneAttribute().getHeader());
                    break;
                case command_protocol_1.CommandProtocol.SET_ICON:
                    const icon = image_1.Image.fromSpecification(pGUICommand.getOneAttributeAsNode());
                    this.mFrontendWebGUI.setIcon(icon);
                    break;
                case command_protocol_1.CommandProtocol.SET_ROOT_HTML_ELEMENT:
                    this.mFrontendWebGUI.setRootHTMLElementFromString(pGUICommand.getOneAttribute().getHeader());
                    break;
                case command_protocol_1.CommandProtocol.SET_HTML_ELEMENT:
                    const localHTMLElementId = pGUICommand.getAttributeAt(1).getHeader();
                    const localHTMLElementAsString = pGUICommand.getAttributeAt(2).getHeader();
                    this.mFrontendWebGUI.setHTMLElementFromString(localHTMLElementId, localHTMLElementAsString);
                    break;
                case command_protocol_1.CommandProtocol.SET_CSS:
                    const lCSS = pGUICommand.getOneAttribute().getHeader();
                    this.mFrontendWebGUI.setCSS(lCSS);
                    break;
                case command_protocol_1.CommandProtocol.SET_EVENT_FUNCTIONS:
                    const eventFunctions = pGUICommand.getAttributes().to(a => event_function_1.EventFunction.fromSpecification(a.toNode()));
                    this.mFrontendWebGUI.setEventFunctions(eventFunctions);
                    break;
                case command_protocol_1.CommandProtocol.SET_USER_INPUT_FUNCTIONS:
                    const userInputFunctions = pGUICommand.getAttributes().to(a => user_input_function_1.UserInputFunction.fromSpecification(a.toNode()));
                    this.mFrontendWebGUI.setUserInputFunctions(userInputFunctions);
                    break;
                default:
                    throw new Error('The given \'' + pGUICommand + '\' is not valid.');
            }
        }
    }
    exports.FrontendWebClientGUIManager = FrontendWebClientGUIManager;
});
define("system/application/web-application-protocol/object-protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ObjectProtocol = void 0;
    class ObjectProtocol {
        static CONTROL = 'Control';
        static GUI = 'GUI';
        static URL = 'URL';
    }
    exports.ObjectProtocol = ObjectProtocol;
});
define("system/application/web-application/receiver-controller", ["require", "exports", "base/data-structure/linked-list/linked-list"], function (require, exports, linked_list_11) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ReceiverController = void 0;
    class ReceiverController {
        runMethod;
        getDataMethod;
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
            const multiData = new linked_list_11.LinkedList();
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
define("system/application/web-application-protocol/request-protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.RequestProtocol = void 0;
    class RequestProtocol {
        static GET_COOKIE_VALUE_BY_COOKIE_NAME = 'GetCookieValueByCookieName';
        static GET_URL_PARAMETER_VALUE_BY_URL_PARAMETER_NAME = 'GetURLParameterValueByURLParameterName';
        RequestProtocol() { }
    }
    exports.RequestProtocol = RequestProtocol;
});
define("system/application/web-application/target-application-extractor", ["require", "exports", "base/data-structure/single-container/single-container", "base/web/url-line-reader"], function (require, exports, single_container_3, url_line_reader_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.TargetApplicationExtractor = void 0;
    class TargetApplicationExtractor {
        static INSTANCE = new TargetApplicationExtractor();
        getOptionalTargetApplicationFromURL() {
            const appContainer = url_line_reader_1.URLLineReader.INSTANCE.getOptionalValueOfURLParameterByName('app');
            if (appContainer.containsAny()) {
                const targetApplication = appContainer.getStoredElement();
                return single_container_3.SingleContainer.withElement(targetApplication);
            }
            return single_container_3.SingleContainer.withoutElement();
        }
    }
    exports.TargetApplicationExtractor = TargetApplicationExtractor;
});
define("system/application/web-application/frontend-web-client", ["require", "exports", "base/document/chained-node/chained-node", "system/application/web-application-protocol/command-protocol", "base/web/cookie-manager", "system/application/web-application/frontend-web-client-gui-manager", "base/data-structure/linked-list/linked-list", "base/net/end-point3/net-end-point3", "base/document/node/node", "system/application/web-application-protocol/object-protocol", "system/application/web-application/receiver-controller", "system/application/web-application-protocol/request-protocol", "base/data-structure/single-container/single-container", "system/application/web-application/target-application-extractor", "base-api/net-api/web-socket-api/web-socket-type", "base/web/url-line-reader"], function (require, exports, chained_node_4, command_protocol_2, cookie_manager_1, frontend_web_client_gui_manager_1, linked_list_12, net_end_point3_1, node_7, object_protocol_1, receiver_controller_1, request_protocol_1, single_container_4, target_application_extractor_1, web_socket_type_2, url_line_reader_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.FrontendWebClient = void 0;
    class FrontendWebClient {
        static toIpAndPort(ip, port) {
            return new FrontendWebClient(ip, port, single_container_4.SingleContainer.withoutElement(), web_socket_type_2.WebSocketType.BASE_WEB_SOCKET);
        }
        static toIpAndPortOnSecureWebSocket(ip, port) {
            return new FrontendWebClient(ip, port, single_container_4.SingleContainer.withoutElement(), web_socket_type_2.WebSocketType.SECURE_WEB_SOCKET);
        }
        static toIpAndPortAndApplication(ip, port, application) {
            return new FrontendWebClient(ip, port, single_container_4.SingleContainer.withElement(application), web_socket_type_2.WebSocketType.BASE_WEB_SOCKET);
        }
        static toIpAndPortAndApplicationOnSecureWebSocket(ip, port, application) {
            return new FrontendWebClient(ip, port, single_container_4.SingleContainer.withElement(application), web_socket_type_2.WebSocketType.SECURE_WEB_SOCKET);
        }
        static toIpAndPortAndApplicationFromURL(ip, port) {
            return new FrontendWebClient(ip, port, target_application_extractor_1.TargetApplicationExtractor.INSTANCE.getOptionalTargetApplicationFromURL(), web_socket_type_2.WebSocketType.BASE_WEB_SOCKET);
        }
        static toIpAndPortAndApplicationFromURLOnSecureWebSocket(ip, port) {
            return new FrontendWebClient(ip, port, target_application_extractor_1.TargetApplicationExtractor.INSTANCE.getOptionalTargetApplicationFromURL(), web_socket_type_2.WebSocketType.SECURE_WEB_SOCKET);
        }
        mGUIManager;
        endPoint;
        constructor(ip, port, optionalTarget, webSocketType) {
            this.mGUIManager = frontend_web_client_gui_manager_1.FrontendWebClientGUIManager.withEventTakerAndFileTakerAndWindow((command) => this.takeEvent(command), (controlFixedId, file) => this.takeFile(controlFixedId, file), window);
            this.endPoint = new net_end_point3_1.NetEndPoint3(ip, port, optionalTarget, webSocketType);
            this.endPoint.setReceiverController(new receiver_controller_1.ReceiverController(c => this.run(c), r => this.getData(r)));
        }
        createSetFileCommand(controlFixedId, file) {
            return chained_node_4.ChainedNode.withHeaderAndNextNode(object_protocol_1.ObjectProtocol.GUI, chained_node_4.ChainedNode.withHeaderAndAttributeAndNextNode('ControlByFixedId', chained_node_4.ChainedNode.withHeader(controlFixedId), chained_node_4.ChainedNode.withHeaderAndAttribute(command_protocol_2.CommandProtocol.SET_FILE, chained_node_4.ChainedNode.withHeader(file))));
        }
        createSetUserInputsCommand() {
            var userInputs = this.mGUIManager.getUserInputs();
            return chained_node_4.ChainedNode.withHeaderAndNextNode(object_protocol_1.ObjectProtocol.GUI, chained_node_4.ChainedNode.withHeaderAndAttributesFromNodes(command_protocol_2.CommandProtocol.SET_USER_INPUTS, userInputs.to(ui => ui.getSpecification())));
        }
        getData(request) {
            switch (request.getHeader()) {
                case request_protocol_1.RequestProtocol.GET_COOKIE_VALUE_BY_COOKIE_NAME:
                    const cookieValue = cookie_manager_1.CookieManager.INSTANCE.getCookieValueByCookieNameOrEmptyString(request.getOneAttribute().getHeader());
                    if (cookieValue.length === 0) {
                        return new node_7.Node();
                    }
                    return node_7.Node.withHeader(cookieValue);
                case request_protocol_1.RequestProtocol.GET_URL_PARAMETER_VALUE_BY_URL_PARAMETER_NAME:
                    const conURLParamterName = request.getOneAttribute().getHeader();
                    const conURLParameterValue = url_line_reader_2.URLLineReader.INSTANCE.getOptionalValueOfURLParameterByName(conURLParamterName);
                    if (conURLParameterValue.containsAny()) {
                        return node_7.Node.withHeader(conURLParameterValue.getStoredElement());
                    }
                    return new node_7.Node();
                default:
                    throw new Error('The given request \'' + request + '\' not valid.');
            }
        }
        openNewTabWithURL(pURL) {
            this.mGUIManager.openNewTabWithURL(pURL);
        }
        run(command) {
            switch (command.getHeader()) {
                case object_protocol_1.ObjectProtocol.GUI:
                    this.mGUIManager.runGUICommand(command.getNextNode());
                    break;
                case command_protocol_2.CommandProtocol.OPEN_NEW_TAB:
                    this.runOpenNewTabCommand(command);
                    break;
                case command_protocol_2.CommandProtocol.REDIRECT:
                    this.runRedirectCommand(command);
                    break;
                case command_protocol_2.CommandProtocol.SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE:
                    this.runSetOrAddCookieWithNameAndValueCommand(command);
                    break;
                case command_protocol_2.CommandProtocol.DELETE_COOKIE_BY_NAME:
                    this.runDeleteCookieByNameCommand(command);
                    break;
                case command_protocol_2.CommandProtocol.WRITE_TEXT_TO_CLIPBOARD:
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
            cookie_manager_1.CookieManager.INSTANCE.deleteCookieByName(name);
        }
        runOpenNewTabCommand(openNewTabCommand) {
            const lURL = openNewTabCommand
                .getAttributes()
                .getStoredFirstByCondition(a => a.hasGivenHeader(object_protocol_1.ObjectProtocol.URL))
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
            cookie_manager_1.CookieManager.INSTANCE.setOrAddCookieWithNameAndValue(name, value);
        }
        runWriteTextToClipboardCommand(writeTextToClipboardCommand) {
            const text = writeTextToClipboardCommand.getOneAttribute().getHeader();
            this.writeTextToClipboard(text);
        }
        takeEvent(command) {
            const commands = new linked_list_12.LinkedList();
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
define("system-api/guiapi/structure-property/direction-in-rectangle", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.DirectionInRectangle = void 0;
    var DirectionInRectangle;
    (function (DirectionInRectangle) {
        DirectionInRectangle[DirectionInRectangle["HORIZONTAL"] = 0] = "HORIZONTAL";
        DirectionInRectangle[DirectionInRectangle["VERTICAL"] = 1] = "VERTICAL";
        DirectionInRectangle[DirectionInRectangle["DIAGONAL_UP"] = 2] = "DIAGONAL_UP";
        DirectionInRectangle[DirectionInRectangle["DIAGONAL_DOWN"] = 3] = "DIAGONAL_DOWN";
    })(DirectionInRectangle || (exports.DirectionInRectangle = DirectionInRectangle = {}));
});
define("system/graphic/color/color-gradient", ["require", "exports", "system/graphic/color/color", "system-api/guiapi/structure-property/direction-in-rectangle"], function (require, exports, color_2, direction_in_rectangle_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ColorGradient = void 0;
    class ColorGradient {
        static fromSpecification(specification) {
            return new ColorGradient(direction_in_rectangle_1.DirectionInRectangle[specification.getStoredAttributeAtIndex(1).getHeader()], color_2.Color.fromString(specification.getStoredAttributeAtIndex(2).getHeader()), color_2.Color.fromString(specification.getStoredAttributeAtIndex(3).getHeader()));
        }
        static withDirectionAndColor1AndColor2(direction, color1, color2) {
            return new ColorGradient(direction, color1, color2);
        }
        direction;
        color1;
        color2;
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
define("system-test/graphic-test/color-test/color-test", ["require", "exports", "system/graphic/color/color", "base/testing/test/test"], function (require, exports, color_3, test_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ColorTest = void 0;
    class ColorTest extends test_6.Test {
        testCase_getHTMLCode() {
            const color = new color_3.Color(0, 0, 0, 255);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#000000');
        }
        testCase_getHTMLCode_2() {
            const color = new color_3.Color(255, 255, 255, 255);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#FFFFFF');
        }
        testCase_getHTMLCode_3() {
            const color = new color_3.Color(1, 1, 1, 255);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#010101');
        }
        testCase_getHTMLCode_4() {
            const color = new color_3.Color(0, 64, 128, 255);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#004080');
        }
    }
    exports.ColorTest = ColorTest;
});
define("system-test/graphic-test/color-test/color-test-pool", ["require", "exports", "system-test/graphic-test/color-test/color-test", "base/testing/test/test-pool"], function (require, exports, color_test_1, test_pool_9) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.ColorTestPool = void 0;
    class ColorTestPool extends test_pool_9.TestPool {
        constructor() {
            super();
            this.addTest(new color_test_1.ColorTest());
        }
    }
    exports.ColorTestPool = ColorTestPool;
});
define("system-test/graphic-test/graphic-test-pool", ["require", "exports", "system-test/graphic-test/color-test/color-test-pool", "base/testing/test/test-pool"], function (require, exports, color_test_pool_1, test_pool_10) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.GraphicTestPool = void 0;
    class GraphicTestPool extends test_pool_10.TestPool {
        constructor() {
            super();
            this.addTestPool(new color_test_pool_1.ColorTestPool());
        }
    }
    exports.GraphicTestPool = GraphicTestPool;
});
define("system-test/system-test-pool", ["require", "exports", "system-test/graphic-test/graphic-test-pool", "base/testing/test/test-pool"], function (require, exports, graphic_test_pool_1, test_pool_11) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.SystemTestPool = void 0;
    class SystemTestPool extends test_pool_11.TestPool {
        constructor() {
            super();
            this.addTestPool(new graphic_test_pool_1.GraphicTestPool());
        }
    }
    exports.SystemTestPool = SystemTestPool;
});
define("system-test/launcher", ["require", "exports", "system-test/system-test-pool"], function (require, exports, system_test_pool_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new system_test_pool_1.SystemTestPool().run();
});
define("system-test/graphic-test/launcher", ["require", "exports", "system-test/graphic-test/graphic-test-pool"], function (require, exports, graphic_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new graphic_test_pool_2.GraphicTestPool().run();
});
define("system-test/graphic-test/color-test/launcher", ["require", "exports", "system-test/graphic-test/color-test/color-test-pool"], function (require, exports, color_test_pool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new color_test_pool_2.ColorTestPool().run();
});
define("union-test/union-test-pool", ["require", "exports", "base-test/core-test-pool", "system-test/system-test-pool", "base/testing/test/test-pool"], function (require, exports, core_test_pool_2, system_test_pool_2, test_pool_12) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.UnionTestPool = void 0;
    class UnionTestPool extends test_pool_12.TestPool {
        constructor() {
            super();
            this.addTestPool(new core_test_pool_2.CoreTestPool());
            this.addTestPool(new system_test_pool_2.SystemTestPool());
        }
    }
    exports.UnionTestPool = UnionTestPool;
});
define("union-test/launcher", ["require", "exports", "union-test/union-test-pool"], function (require, exports, union_test_pool_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new union_test_pool_1.UnionTestPool().run();
});
