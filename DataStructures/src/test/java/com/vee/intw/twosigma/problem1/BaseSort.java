package com.vee.intw.twosigma.problem1;

public abstract class BaseSort {

	protected void exchange(AlphaNum[] array, int i, int j) {
		AlphaNum temp = array[i];
		array[i] = array[j];
		array[j] = temp;
/*		if(temp instanceof Alpha) {
			int position = ((Alpha)array[i]).getPosition();
			((Alpha)array[i]).setPosition(((Alpha)array[j]).getPosition());
			((Alpha)array[j]).setPosition(position);
		} 
		else
		 {
			int position = ((Alpha)array[i]).getPosition();
			((Alpha)array[i]).setPosition(((Alpha)array[j]).getPosition());
				((Alpha)array[j]).setPosition(position);
		}*/
		int position = array[i].getPosition();
		array[i].setPosition(array[j].getPosition());
		array[j].setPosition(position);
	}
}
