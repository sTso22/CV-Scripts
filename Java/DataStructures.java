import java.util.Scanner;

public class DataStructures {
	
//------------------------Binary Tree---------------------------------
	
	class Tnode{	// Binary Tree Node
		int value;
		Tnode left;
		Tnode right;
		
		Tnode(int value){ // Tree Node init
			this.value = value;
			left = null;
			right = null;
		}
		
		Tnode insertToBT(Tnode newnode,BT root) {
			// FUNCTION:
			// Add new node to the Binary Tree
			// Arguments: Tnode newnode amd the BT root
			Tnode current = root.root;

			if(current == null) {	// insert at root
				return newnode;
			}
			else {
				Tnode last = current;	// previous node 
				while(current!=null) {
					if(newnode.value<current.value) {
						last = current;
						current = current.left;
					}
					else {
						last = current;
						current = current.right;
					}
				}
				if(newnode.value < last.value) {	// insert to the left
					last.left = newnode;
				}
				else {								// insert to the right
					last.right = newnode;	
				}
				
				return root.root;
			}
		}
		
		void printBT(Tnode root) {
			// FUNCTION:
			// Print the whole BT in preorder (root first then left) traversal mode
			// Arguments: the root of the BT
			Tnode current = root;
			DataStructures.Tnode t = new Tnode(-1); // temporary node to call the function
			if(current!=null) {
				System.out.print(" " + current.value);
				t.printBT(current.left);
				t.printBT(current.right);
			}
		}
		
		Tnode deleteTnode(Tnode current, int value) {
			// FUNCTION:
			// Find and delete the binary tree node that has the given value
			// Arguments: The BT root and the number to delete
			if(current == null) {
				return null;
			}
			if(current.value == value) {
				if(current.left == null && current.left == null) {	// no children
					return null;
				}
				if(current.right == null) {	// left child
					return current.left;
				}
				if(current.left == null) {	// right child
					return current.right;
				}
				if(current.right!=null && current.left!=null) {	// two children
					int min = findMin(current.right);
					current.value = min;
					current.right = deleteTnode(current.right,min);
					
				}
			}
			if(current.value > value) {	// to the left tree
				current.left = deleteTnode(current.left,value);
				return current;
			}
			current.right = deleteTnode(current.right,value);	// to the right tree
			return current;
		}
		
		int findMin(Tnode node) {
			// FUNCTION: 
			// Finds the minimum value of the right-most tree and returns it 
			// Arguments: a tree node
			if(node.left!=null) {	// if node has left child
				return findMin(node.left);
			}
			return node.value;
		}
		
	}

	class BT{	// Root of the BT
		Tnode root;
	}
	
//----------------------------Queue------------------------------------
	
	class Qnode{	// Queue Node
		int value;
		Qnode previous;
		Qnode next;
		
		Qnode(int value){	// Queue node init
			this.value = value;
			previous = null;
			next = null;
		}
		
		Qnode insertQnode(Queue head,Qnode newnode) {
			// FUNCTION: 
			// Insert new node in the head of queue (descending order)
			// Arguments: the head of the queue head, and the new node to add new node
			if(head.value == null) {
				head.value = newnode;
				return newnode;
			} 
			else{
				Qnode current = head.value;

				newnode.next = current;
				newnode.previous = null;
				current.previous = newnode;
				head.value = newnode;
				return head.value;
			}
		}
		
		Qnode sortQueue(Queue head,int mode) {
			// FUNCTION:
			// Sort a queue in ascending/descending order depending on user's choice
			// Arguments: the head of the queue and the sorting mode: 0 -> Ascending
			//														  1 -> Descending
			int flag = 0; //flag to stop looping the queue

			while(flag == 0) {
				Qnode current = head.value;
				
				//current.printQueue(head);
				//System.out.print("\n");
				
				if(mode == 0) {	//Ascending
					while(current!=null) {
						if(current.next!=null) {
							Qnode next = current.next; 
							if(current.value>next.value) {
								if(current.previous == null) {
									head.value = next;
									current.next = next.next;
									current.previous = next;
									next.previous = null;
									next.next = current;
								}
								else {
									current.next = next.next;
									if(next.next!=null) {
										next.next.previous = current;
									}
									
									next.previous = current.previous;
									current.previous.next = next;
									
									current.previous = next;
									next.next = current;
								}
							}
							else {
								current = current.next;
							}
						}
						else {
							current = current.next;
						}

					}
					current = head.value;
					int count = 0;
					int sort_count = 0;
					while(current!=null) {
						if(current!=null) {
							count ++;
						}
						if(current.next!=null) {
							if(current.value<current.next.value) {
								sort_count ++;
							}
							current = current.next;
						}
						else {
							if(current.value>current.previous.value) {
								sort_count ++;
							}
							current = current.next;
						}
					}
					if(count == sort_count) {
						flag = 1;
					}		
				}
				else {		//descending
					while(current!=null) {
						if(current.next!=null) {
							Qnode next = current.next; 
							if(current.value<next.value) {
								if(current.previous == null) {
									head.value = next;
									current.next = next.next;
									if(next.next!=null) {
										next.next.previous = current;
									}
									current.previous = next;
									next.previous = null;
									next.next = current;
								}
								else {
									current.next = next.next;
									if(next.next!=null) {
										next.next.previous = current;
									}
									
									next.previous = current.previous;
									current.previous.next = next;
									
									current.previous = next;
									next.next = current;
								}
							}
							else {
								current = current.next;
							}
						}
						else {
							current = current.next;
						}

					}
					current = head.value;
					int count = 0;
					int sort_count = 0;
					while(current!=null) {
						if(current!=null) {
							count ++;
						}
						if(current.next!=null) {
							if(current.value>current.next.value) {
								sort_count ++;
							}
							current = current.next;
						}
						else {
							if(current.value<current.previous.value) {
								sort_count ++;
							}
							current = current.next;
						}
					}
					if(count == sort_count) {
						flag = 1;
					}
				}
			}
			return head.value;
		} 
		
		void printQueue(Queue head) {
			// FUNCTION:
			// Loop through the queue and print the stored values
			// Arguments: the head of the queue
			Qnode current = head.value;
			while(current!=null) {
				System.out.print(current.value+" ");
				current = current.next;
			}
		}
		
		Qnode deleteFromQueue(Queue head) {
			// FUNCTION:
			// Delete from the head of the queue Or a specific number
			// Arguments: the head of the queue
			Scanner sc = new Scanner(System.in);
			System.out.print("Do you want to delete a specific number from the queue?y/n (n deletes the head of the queue)");
			String ch = sc.next();
			char choice = ch.charAt(0);
			boolean a = ch.equals("y");
			if(a) {
				System.out.print("Enter the number you want to delete");
				int num = sc.nextInt();
				Qnode current = head.value;
				int flag = 0;
				while(flag == 0) {
					if(current!=null) {
						if(current.value == num) {
							if(current.next!=null) {
								current.next.previous = current.previous;
								if(current.previous!=null) {
									current.previous.next = current.next;
								}
							}
							else {
								current.previous.next = null;
							}
							flag = 1;
							current = null;
							System.out.println(num+" Found");
						}
						else {
							current = current.next;
						}
					}
					else {
						System.out.println(num+" Not Found");
						break;
					}
				}
				return head.value;
			}
			else {	// delete from head
				Qnode current = head.value;
				if(current.next!=null) {
					current.next.previous = null;
					head.value = current.next;
					current = null;
				}
				else {
					head.value = null;
				}
				return head.value;
			}
		
		}
		
	}
	
	class Queue{	// Queue Head
		Qnode value;
	}
	
	public static void main(String[] args) {
		int app_flag =0;
		while(app_flag == 0) {
			System.out.println("--------------User Menu------------------");
			System.out.println("1. Queue Functions");
			System.out.println("2. Binary Tree Functions");
			System.out.println("3. End Application");
			System.out.println("-----------------------------------------");
			System.out.println("Select a Function");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if(choice == 1) {
				DataStructures ds = new DataStructures();
				DataStructures.Queue h = ds.new Queue();	//head of the queue
				h.value = null;
				int flag = 0;
				while(flag == 0) {
					System.out.println("Insert a value to the queue: (Enter -1 to end)");
					int num = sc.nextInt();
					if(num!=-1) {
						DataStructures.Qnode q = ds.new Qnode(num);
						h.value  = q.insertQnode(h, q);
					}
					else {
						flag = 1;
					}
				}
				System.out.println("Select 0 for Ascending Sorting of the Queue Or 1 for Descending");
				int s_choice = sc.nextInt();
				DataStructures.Qnode q = ds.new Qnode(-1);
				System.out.println("Initial Queue");
				q.printQueue(h);
				System.out.print("\n");
				h.value = q.sortQueue(h, s_choice);
				System.out.println("Sorted Queue");
				q.printQueue(h);
				System.out.println("\n");
				h.value = q.deleteFromQueue(h);
				System.out.println("Queue before deletion");
				q.printQueue(h);
				System.out.println("\n");
				System.out.println("Queue after deletion");
				q.printQueue(h);
				System.out.println("\n");
			}
			if(choice == 2) {
				System.out.println("Insert a value to the Binary Tree: (Enter -1 to end)");
				DataStructures ds = new DataStructures();
				DataStructures.BT root = ds.new BT();	//head of the queue
				root.root = null;
				int flag = 0;
				while(flag == 0) {
					int num = sc.nextInt();
					if(num!=-1) {
						DataStructures.Tnode t = ds.new Tnode(num);
						root.root  = t.insertToBT(t, root);
					}
					else {
						flag = 1;
					}
				}
				System.out.println("The Binary Tree contains the values: ");
				DataStructures.Tnode t = ds.new Tnode(-1);
				t.printBT(root.root);
				System.out.println("\n");
				
				System.out.println("Enter the value you want to delete from the Binary Tree: ");
				int num = sc.nextInt();
				root.root  = t.deleteTnode(root.root, num);
				System.out.println("The remaining value in the Binary Tree are : ");
				t.printBT(root.root);
				System.out.println("\n");
			}
			if(choice == 3) {
				sc.close();
				app_flag = 1;
			}
		}

		

	}

}
