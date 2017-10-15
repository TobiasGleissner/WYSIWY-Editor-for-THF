package util.either;

import util.either.Either;

public class Left<L,R> implements Either<L,R>
{
    public L left;
    public Left(L left) { this.left = left; }

    public L left() { return this.left; }
    public R right() { return null; }
}
