package jp.dodododo.dao.flyweight;

public class NullFlyweightFactory extends FlyweightFactory {
	@Override
	public <T> T getFlyweight(T value) {
		return value;
	}

	@Override
	public void clear() {
	}
}
