package jp.dodododo.dao.commons;

public enum Bool {

	TRUE {
		@Override
		public boolean toBoolean(boolean defaultValue) {
			return true;
		}
	},
	FALSE {
		@Override
		public boolean toBoolean(boolean defaultValue) {
			return false;
		}
	},
	UNDEFINED {
		@Override
		public boolean toBoolean(boolean defaultValue) {
			return defaultValue;
		}
	};

	public boolean toBoolean(boolean defaultValue) {
		throw new UnsupportedOperationException();
	}
}
