// CSE 143 Final Preparation Answers (LinkedIntList) - Week 9  

public class LinkedIntList {
    private ListNode front;

    // 3. shiftLastOf3
    int shiftLastOf3() {
	    int count = 0;
	    if (front != null && front.next != null && front.next.next != null) {
		    ListNode curr1 = front;
		    ListNode oldFront = curr1;
		    front = front.next.next;
		    ListNode curr2 = front;
		    curr1.next.next = curr2.next;
		    curr1 = curr1.next.next;
		    count++;
		    while (curr1 != null && curr1.next != null && curr1.next.next != null) {
			    curr2.next = curr1.next.next;
			    curr2 = curr2.next;
			    curr1.next.next = curr2.next;
			    curr1 = curr1.next.next;
			    count++;
		    }
		    curr2.next = oldFront;
	    }
	    return count;
    }

    // 4. rotate3
    void rotate3() {
	    if (front != null && front.next != null && front.next.next != null) {
		    ListNode toEnd = front;
		    front = front.next;
		    toEnd.next = toEnd.next.next.next;
		    front.next.next = toEnd;
		    ListNode cur = toEnd;
		    while (cur.next != null && cur.next.next != null && cur.next.next.next != null) {
			    toEnd = cur.next;
			    cur.next = cur.next.next;
			    toEnd.next = toEnd.next.next.next;
			    cur.next.next.next = toEnd;
			    cur = toEnd;
		    }
	    }
    }   

    // post: appends the value to the end of the list
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }
}
