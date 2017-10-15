package util.either;

import util.either.Either;

public class Right<L,R> implements Either<L,R>
{
    public R right;
    public Right(R right) { this.right = right; }

    public L left() { return null; }
    public R right() { return this.right; }
}
