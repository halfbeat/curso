package practica1.logica;

public class Tuple2<T1 extends Object, T2 extends Object> {
	public T1 _1;
	public T2 _2;

	public Tuple2() {
	}

	public Tuple2(T1 a, T2 b) {
		_1 = a;
		_2 = b;
	}

	public T1 get_1() {
		return _1;
	}

	public void set_1(T1 _1) {
		this._1 = _1;
	}

	public T2 get_2() {
		return _2;
	}

	public void set_2(T2 _2) {
		this._2 = _2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_1 == null) ? 0 : _1.hashCode());
		result = prime * result + ((_2 == null) ? 0 : _2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple2<?, ?> other = (Tuple2<?, ?>) obj;
		if (_1 == null) {
			if (other._1 != null)
				return false;
		} else if (!_1.equals(other._1))
			return false;
		if (_2 == null) {
			if (other._2 != null)
				return false;
		} else if (!_2.equals(other._2))
			return false;
		return true;
	}

}
