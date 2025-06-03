package tests.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.java_revision.Employee;

public class TestCompletableFuture {

    public static void threadSleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // **Beginner (1–7): Basic Usage & Supply/RunAsync**
    // 1. Create a `CompletableFuture` that returns a string message asynchronously.
    // 4. Chain `thenAccept()` to consume a value.
    @Test
    public void testSupplyAsync() {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            System.out.println("Waiting In Thread: " + Thread.currentThread().getName());
            threadSleep(1000);
            return "Hello From Simple Completebale Future";
        });

        result.thenAccept(s -> System.out.println("Result " + s + " in Thread: " + Thread.currentThread().getName()));
        System.out.println(result.join());

    }

    // 2. Use `runAsync()` to print a message in another thread.
    @Test
    public void testRunAsync() {
        CompletableFuture.runAsync(() -> {
            threadSleep(2000);
            System.out.println("Message running in Thread: " + Thread.currentThread().getName());
        }).join();
    }

    // 3. Chain `thenApply()` to transform a string.
    // 4. Chain `thenAccept()` to consume a value.
    @Test
    public void testThenApply() {
        CompletableFuture<Void> result = CompletableFuture.supplyAsync(() -> {
            threadSleep(2000);
            return "then apply string to transform";
        })
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
        result.join();
    }

    // 5. Demonstrate `thenRun()` after a `CompletableFuture` completes.
    @Test
    public void testThenRun() {
        CompletableFuture.runAsync(() -> {
            threadSleep(2000);
            System.out.println("in Supply Async");
        }).thenRun(() -> {
            System.out.println("In Run Async");
        }).join();
    }

    // 6. Get the result using `get()` and `join()`.
    @Test
    public void testGetJoinResult() {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
            return 20;
        });

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("In Join: " + result.join());
    }

    // 7. Handle exceptions with `exceptionally()`.
    @Test
    public void testHandleException() {
        CompletableFuture<String> res = CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(5);
            if (i > 1)
                throw new IllegalStateException("Intended Exeption " + i);
            return "Hello: " + i;
        }).exceptionally(ex -> {
            System.out.println("Exception Occurred: " + ex.getMessage());
            return "falback value";
        });

        System.out.println(res.join());
    }

    // **Intermediate (8–14): Combining and Chaining**
    // 8. Combine two futures using `thenCombine()`- Return Result .
    @Test
    public void testCombineFutures() {
        CompletableFuture<List<Integer>> listFuture = CompletableFuture.supplyAsync(() -> {
            return IntStream.range(0, 20).parallel().boxed().toList();
        });

        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> {
            return "PrefixString";
        });

        CompletableFuture<List<String>> result = listFuture.thenCombine(stringFuture, (a, b) -> {
            return a.stream().map(i -> b.concat(String.valueOf(i))).toList();
        });

        System.out.println(result.join());
    }

    // 9. Run two futures in parallel and combine results with `thenAcceptBoth()`.
    // No return result
    @Test
    public void testThenAcceptBoth() {
        CompletableFuture<List<Integer>> listFuture = CompletableFuture.supplyAsync(() -> {
            return IntStream.range(0, 200).parallel().boxed().toList();
        });

        CompletableFuture<List<String>> stringFuture = CompletableFuture.supplyAsync(() -> {
            String str = "WaterBottleSubstrings";
            return IntStream.range(0, str.length()).boxed()
                    .flatMap(i -> IntStream.rangeClosed(i + 1, str.length()).mapToObj(j -> str.substring(i, j)))
                    .toList();
        });

        listFuture.thenAcceptBoth(stringFuture, (a, b) -> {
            System.out.println(a);
            System.out.println(b);
        }).join();

    }

    // 10. Use `applyToEither()` to return the fastest result.
    @Test
    public void testapplyToEither() {
        CompletableFuture<List<String>> listFuture = CompletableFuture.supplyAsync(() -> {
            threadSleep(5000);
            return Arrays.asList("banana", "apple", "mango");
        });

        CompletableFuture<List<String>> stringFuture = CompletableFuture.supplyAsync(() -> {
            String str = "waterbottle";
            return IntStream.range(0, str.length()).boxed()
                    .flatMap(i -> IntStream.rangeClosed(i + 1, str.length()).mapToObj(j -> str.substring(i, j)))
                    .toList();
        });

        CompletableFuture<List<String>> result = listFuture.applyToEither(stringFuture, list -> {
            return list.stream().map(String::toUpperCase).toList();
        });
        System.out.println(result.join());
    }

    // 11. Chain multiple stages with `thenApply()` and `thenCompose()`.
    @Test
    public void testApplyAndCompose() {
        CompletableFuture<Integer> getUserId = CompletableFuture.supplyAsync(() -> {
            return 42;
        });

        CompletableFuture<String> userdetails = getUserId
                .thenApply(id -> id + 1)
                .thenCompose(id -> CompletableFuture.supplyAsync(() -> {
                    return "[User ID: " + id + " User Name: Bob]";
                }));

        System.out.println(userdetails.thenApply(String::toUpperCase).join());
    }

    // 12. Use `completeExceptionally()` to force a failure.
    @Test
    public void testCompleteExceptionally() {
        CompletableFuture<String> future = new CompletableFuture<>();

        // Force failure
        future.completeExceptionally(new RuntimeException("Throwing exception"));

        future.exceptionally(ex -> {
            System.out.println("Exception: " + ex.getMessage());
            return "fallback";
        }).thenAccept(result -> {
            System.out.println("Result: " + result);
        }).join();

    }

    // 13. Handle exceptions using `handle()`.
    @Test
    public void handleExceptionwithHandle() {
        CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(5);
            if (i > 2)
                throw new IllegalStateException("Intended Exeption " + i);
            return "Hello: " + i;
        }).handle((res, ex) -> ex == null ? res : ex.getMessage())
                .thenAccept(System.out::println);
    }

    // 14. Use `whenComplete()` to log final result or error.
    @Test
    public void testWhenComplete() {
        CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(5);
            if (i > 2)
                throw new IllegalStateException("Intended Exeption " + i);
            return "Hello: " + i;
        }).whenComplete((result, exception) -> {
            if (exception != null) {
                System.out.println("Logging exception: " + exception.getMessage());
            } else {
                System.out.println("Logging success: " + result);
            }
        }).join();
    }

    // **Advanced (15–20): Asynchronous Pipelines & Timeout**
    // 15. Use `supplyAsync()` with a custom `ExecutorService`.
    @Test
    public void testSupplyAsyncWithExecutorService() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture.supplyAsync(() -> {
            return IntStream.range(0, 500).boxed().toList();
        }, executor).thenAccept(
                list -> list.forEach(
                        i -> System.out.println(i + " Thread Name " + Thread.currentThread().getName())))
                .join();
        executor.shutdown();
    }

    // 16. Write a function that performs 3 steps in a pipeline using
    // `thenCompose()`.
    @Test
    public void useThenComposePipeline() {
        CompletableFuture.supplyAsync(() -> {
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(new Employee("A", 123, 30000));
            employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com"));
            employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com"));
            employeeList.add(new Employee("D", 126, 5000));
            employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com"));
            return employeeList;
        }).thenCompose(elist -> CompletableFuture.supplyAsync(() -> {
            return elist.stream().filter(e -> e.getEmailId() != null).toList();
        })).thenCompose(elist -> CompletableFuture.supplyAsync(() -> {
            return elist.stream().map(Employee::getEmailId).map(String::toUpperCase).toList();
        })).thenCompose(elist -> CompletableFuture.runAsync(() -> {
            elist.forEach(System.out::println);
        })).join();
    }

    // 17. Simulate a slow task and use `orTimeout()` or `completeOnTimeout()`.
    @Test
    public void testTimeout() {
        CompletableFuture.runAsync(() -> {
            threadSleep(8000);
            IntStream.range(0, 20).forEach(System.out::println);
        }).orTimeout(5, TimeUnit.SECONDS).handle(
                (res, ex) -> {
                    if (ex != null) {
                        return "Task timed out";
                    }
                    return "Task completed";
                })
                .thenAccept(System.out::println).join();

        CompletableFuture.supplyAsync(() -> {
            threadSleep(6000);
            return 10;
        }).completeOnTimeout(-1, 5, TimeUnit.SECONDS).thenAccept(System.out::println).join();

    }

    // 18. Cancel a `CompletableFuture` before it completes.
    @Test
    public void testCancelCompletableFuture() {
        CompletableFuture<Void> downloadFuture = CompletableFuture.runAsync(() -> {
            // threadSleep(5000);
            System.out.println("Dowlaoding File");
        });

        CompletableFuture.runAsync(() -> {
            // threadSleep(3000);
            if (!downloadFuture.isDone()) {
                System.out.println("Cancelling download due to timeout...");
                downloadFuture.cancel(true);
            }
        });

        downloadFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                if (ex instanceof CancellationException) {
                    System.out.println("Download was cancelled.");
                } else {
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            } else {
                System.out.println("Download completed successfully.");
            }
        });

        downloadFuture.join();
    }

    // 19. Wait for multiple futures using `allOf()` and collect results.
    @Test
    public void testWaitForMultipleFeaturesUsingAllOf() {
        CompletableFuture<List<Integer>> future1 = CompletableFuture.supplyAsync(() -> {
            return IntStream.range(0, 200).boxed().toList();
            // throw new RuntimeException("Exception Thrown");
        });

        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> {
            String string = "Welcome to Java Programming";
            return IntStream.range(0, string.length()).boxed().flatMap(
                    i -> IntStream.rangeClosed(i + 1, string.length()).mapToObj(j -> string.substring(i, j))).toList();
        });

        CompletableFuture.allOf(future1, future2).thenRun(() -> {
            try {
                List<Integer> list1 = future1.get();
                List<String> list2 = future2.get();
                System.out.println(list2);
                System.out.println(list1);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).join();

    }
    // 20. Use `anyOf()` to return as soon as one future completes.
@Test
    public void testWaitForMultipleFeaturesUsingAnyOf() {
        CompletableFuture<List<Integer>> future1 = CompletableFuture.supplyAsync(() -> {

            threadSleep(1000);
            // return IntStream.range(0, 200).boxed().toList();
            throw new IllegalStateException("Exception Thrown");
        });

        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> {
            String string = "Welcome to Java Programming Good Java 8 Understand";
            return IntStream.range(0, string.length()).boxed().flatMap(
                    i -> IntStream.rangeClosed(i + 1, string.length()).mapToObj(j -> string.substring(i, j))).toList();
        });

        CompletableFuture.anyOf(future1, future2).thenAccept(result -> {
             System.out.println(result);
        }).exceptionally(ex -> {
            System.err.println("An error occurred: " + ex.getMessage());
            return null;
        }).join();
    }
}
