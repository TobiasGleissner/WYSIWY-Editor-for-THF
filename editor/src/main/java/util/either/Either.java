package util.either;

public interface Either<L,R>
{
    public L left();
    public R right();
}
