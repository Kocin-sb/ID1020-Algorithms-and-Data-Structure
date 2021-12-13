package assign;

public class Hashtable {
	private static int getHashValue(String s, int i) {
		return (s.hashCode())% i;
	}
	public static void main(String [] arg) {
		String []str = {"hi","there","i","am", "kocin","how","you" ,"doin"};
		String []keys = new String[str.length];
		int [] collision = new int [str.length];
		for (int i = 0;i<str.length;i++) {
			int key = getHashValue(str[i],str.length-1);
			System.out.println(key+ " "+ str[i]);
			if (keys[key] == null) {
				keys[key]=str[i];
			}
			else {
				collision[key] += 1;
				for(int j =key+1;j<keys.length;j++) {
					if(keys[j]==null) {
						keys[j]=str[i];
						break;
					}
				}
			}
				
			
		}
		System.out.println();
		for(int j = 0; j<keys.length;j++)
			System.out.println(j+" "+keys[j]+" "+collision[j]);
	}

}
