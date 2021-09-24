package info.mapes.phasmophobiaapi.utils

inline fun <T, R> Iterable<T>.mapToSet(transform: (T) -> R): Set<R> = map(transform).toSet()

inline fun <T> Iterable<T>.filterToSet(predicate: (T) -> Boolean): Set<T> = filter(predicate).toSet()