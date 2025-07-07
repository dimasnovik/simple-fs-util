# SimpleFilterUtil

SimpleFilterUtil is a Java command-line utility that reads input text files, filters lines by type (integers, floats, strings), collects statistics, and writes the filtered data into separate output files. It supports command-line options for flexible configuration and tracks processing errors.

## Features

- Parses multiple input files line by line.
- Classifies each line as an integer, float, or string.
- Collects statistics on parsed data (optional).
- Writes filtered lines into separate output files by type.
- Supports appending or overwriting output files.
- Provides command-line argument parsing using Apache Commons CLI.


## Requirements

- Java 17 or higher.
- Apache Commons CLI library (version 1.9+).
  

## Usage

```bash
java -jar simplefilterutil.jar [options] inputFile1 [inputFile2 ...]
```


### Command-line options

| Option | Description | Example |
| :-- | :-- | :-- |
| `-f` | Enable full statistics output | `-f` |
| `-a` | Append to output files instead of overwrite | `-a` |
| `-p` | Prefix for output file names | `-p filtered_` |
| `-o` | Output directory | `-o /path/to/output/dir` |
| `-s` | Enable statistics collection | `-s` |

## Example

```bash
java -jar util.jar -s -p result_ -o output_dir input1.txt input2.txt
```

This command will:

- Enable statistics collection (`-s`).
- Use prefix `result_` for output files (`-p`).
- Save output files in `output_dir` (`-o`).
- Process files `in1.txt` and `in2.txt`.

The utility will create (or append to) the following files in `output_dir`:

- `result_integers.txt` — containing all integer lines.
- `result_floats.txt` — containing all float lines.
- `result_strings.txt` — containing all string lines.

If there is no directory givane in `output_dir`, it will be created.

After processing, it prints statistics (if enabled) and the total number of errors encountered.

## Output Files

Output files are named as:

```
<prefix><type>.txt
```

where `<type>` is one of:

- `integers`
- `floats`
- `strings`

If the append option (`-a`) is set, output files will be appended; otherwise, they will be overwritten.


## Building from Source

Make sure to include dependencies for Apache Commons CLI and your utility classes.

Example with Maven:

```xml
<dependency>
    <groupId>commons-cli</groupId>
    <artifactId>commons-cli</artifactId>
    <version>1.9</version>
</dependency>
```
## Build used
- Java version: 17.0.8, vendor: Amazon.com Inc.
- Apache Maven 3.8.1.

## Notes

- Input files should be text files. Supported extensions : "txt", "csv", "rtf", "java", "py"
- Empty lines are skipped.



**Enjoy filtering your data easily with SimpleFilterUtil!**

