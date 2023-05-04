package h_w;

public class t18 {
public static void main(String[] args) {
	char[] m={'a','b','c'};
	char[] n={'x','y','z'};
	for (int i = 0; i < n.length; i++) {
		for (int j = 0; j < n.length; j++) {
			if (m[i] =='a' && n[j] =='x'||m[i]=='a' &&n[j]=='y') {
				continue;
				
			} else if(m[i]=='c' &&n[j]=='x' || m[i]=='c' && n[j]=='z'){
				continue;

			}else if(m[i]=='b'&&n[j]=='y' || m[i]=='b' &&n[j]=='z'){
				continue;
			}else {
				System.out.println(m[i]+" VS "+n[j]);
			}
		}
	}
	
}
}
