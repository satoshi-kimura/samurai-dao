package jp.dodododo.dao.function;

import java.util.List;
import java.util.function.Consumer;

import jp.dodododo.dao.IterationCallback;

public class  ConsumerWrapper<T> implements IterationCallback<T> {
	
	protected Consumer<T> consumer;
	
	public ConsumerWrapper(Consumer<T> consumer) {
		if(consumer == null) {
			throw new IllegalArgumentException();
		}
		this.consumer = consumer;
	}
	
	public void iterate(T obj) {
		consumer.accept(obj);
	}

	public List<T> getResult() {
		return null;
	}
}