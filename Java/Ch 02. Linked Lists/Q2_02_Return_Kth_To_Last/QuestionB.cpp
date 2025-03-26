#include <iostream>
using namespace std;

struct node {
	node * next;
	int data;
};

node* nthToLast(node* head, int k, int& i) {
	if (head == NULL) {
		return NULL;
	}
	node * nd = nthToLast(head->next, k, i);
	i = i + 1;
	if (i == k) {
		return head;
	}
	return nd;
}
	/**
	 * 寻找链表中倒数第k个节点
	 * @param head 链表的头节点指针
	 * @param k 倒数的位置
	 * @param i 计数器，用于在递归返回时记录当前节点的位置
	 * @return 返回倒数第k个节点的指针，如果不存在，则返回NULL
	 *
	 * 本函数使用递归方法来解决问题，首先递归调用直到链表末尾，
	 * 然后在返回的过程中计数，当计数等于k时返回当前节点。
	 * 这种方法利用了递归的特性，可以在不事先知道链表长度的情况下找到倒数第k个节点。
	 */
	node* nthToLast(node* head, int k, int& i) {
		// 如果链表为空，则返回NULL
		if (head == NULL) {
			return NULL;
		}
		
		// 递归调用，寻找倒数第k个节点
		node * nd = nthToLast(head->next, k, i);
		
		// 在返回的过程中计数
		i = i + 1;
		
		// 如果计数等于k，说明当前节点就是我们要找的节点
		if (i == k) {
			return head;
		}
		
		// 返回找到的节点，如果没有找到，则返回NULL
		return nd;
	}
node* nthToLast(node* head, int k) {
	int i = 0;
	return nthToLast(head, k, i);
}

node* createList(int count) {
	node* head = new node();
	head->data = 0;
	node* last = head;
	for (int i = 1; i < count; i++) {
		node* n = new node();
		n->data = i;
		last->next = n;
		last = n;
	}
	return head;
}

void printList(node* head) {
	while (head != NULL) {
		printf("%d", head->data);
		head = head->next;
	}
}

int main() {
	int count = 5;
	node* head = createList(count);
	printList(head);
	printf("\n");
	for (int k = 0; k <= count; k++) {
		node* n = nthToLast(head, k);
		if (n != NULL) {
			int data = n->data;
			printf("%d: ", k);
			printf("%d", n->data);
			printf("\n");
		}
	}
	return 0;
}